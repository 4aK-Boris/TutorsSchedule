package dmitriy.losev.students.presentation.viewmodels.groups

import dmitriy.losev.core.EMPTY_STRING
import dmitriy.losev.core.models.Group
import dmitriy.losev.students.R
import dmitriy.losev.students.core.StudentsAndGroupsState
import dmitriy.losev.students.core.StudentsNavigationListener
import dmitriy.losev.students.core.exception.GROUP_IS_NOT_LOADING_EXCEPTION_CODE
import dmitriy.losev.students.core.exception.GroupIsNotLoadingException
import dmitriy.losev.students.domain.usecases.StudentsNavigationUseCases
import dmitriy.losev.students.domain.usecases.groups.DeleteGroupUseCase
import dmitriy.losev.students.domain.usecases.groups.GetFilterGroupsUseCase
import dmitriy.losev.students.domain.usecases.groups.GetGroupsUseCase
import dmitriy.losev.students.domain.usecases.groups.GroupBringBackFromArchiveUseCase
import dmitriy.losev.students.domain.usecases.groups.GroupMoveToArchiveUseCase
import dmitriy.losev.ui.core.BaseViewModel
import dmitriy.losev.ui.core.runOnIO
import dmitriy.losev.ui.core.runOnIOWithLoading
import dmitriy.losev.ui.core.runOnMain
import dmitriy.losev.ui.views.menu.MenuStateHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine

class GroupsScreenViewModel(
    private val menuStateHandler: MenuStateHandler,
    private val studentsNavigationUseCases: StudentsNavigationUseCases,
    private val getGroupsUseCase: GetGroupsUseCase,
    private val groupMoveToArchiveUseCase: GroupMoveToArchiveUseCase,
    private val groupBringBackFromArchiveUseCase: GroupBringBackFromArchiveUseCase,
    private val deleteGroupUseCase: DeleteGroupUseCase,
    private val getFilterGroupsUseCase: GetFilterGroupsUseCase,
) : BaseViewModel() {

    private val _studentsAndGroupsState = MutableStateFlow(value = StudentsAndGroupsState.ACTIVE)

    private val _activeGroups = MutableStateFlow(value = emptyList<Group>())
    private val _archiveGroups = MutableStateFlow(value = emptyList<Group>())

    private val _filterText = MutableStateFlow(value = EMPTY_STRING)

    private val _menuGroupPopUpVisible = MutableStateFlow(value = false)
    private val _archivePopUpVisible = MutableStateFlow(value = false)
    private val _deletePopUpVisible = MutableStateFlow(value = false)

    private val _group = MutableStateFlow<Group?>(value = null)

    val studentsAndGroupsState  = _studentsAndGroupsState.asStateFlow()

    val filterText = _filterText.asStateFlow()

    val activeGroups = combine(_activeGroups, _filterText) { groups, text ->
        getFilterGroupsUseCase.getFilterGroups(filterString = text, groups = groups)
    }

    val archiveGroups = combine(_archiveGroups, _filterText) { groups, text ->
        getFilterGroupsUseCase.getFilterGroups(filterString = text, groups = groups)
    }

    val menuGroupPopUpVisible = _menuGroupPopUpVisible
    val archivePopUpVisible = _archivePopUpVisible.asStateFlow()
    val deletePopUpVisible = _deletePopUpVisible.asStateFlow()
    val group = _group.asStateFlow()

    fun onFilterTextChanged(filterText: String) {
        _filterText.value = filterText
    }

    private fun onGroupChanged(group: Group) {
        _group.value = group
    }

    private fun onActiveGroupChanged(groups: List<Group>) {
        _activeGroups.value = groups
    }

    private fun onArchiveGroupsChanged(groups: List<Group>) {
        _archiveGroups.value = groups
    }

    fun onStudentsAndGroupsStateChanged(studentsAndGroupsState: StudentsAndGroupsState) {
        _studentsAndGroupsState.value = studentsAndGroupsState
    }

    fun navigateToAddGroupScreen(studentsNavigationListener: StudentsNavigationListener) = runOnMain {
        studentsNavigationUseCases.navigateToAddGroupScreen(studentsNavigationListener)
    }

    fun navigateToEditGroupScreen(studentsNavigationListener: StudentsNavigationListener) = runOnMain {
        checkGroupForNullable { group ->
            _menuGroupPopUpVisible.value = false
            studentsNavigationUseCases.navigateToUpdateGroupScreen(studentsNavigationListener, group.id)
        }
    }

    fun navigateToGroupScreen(studentsNavigationListener: StudentsNavigationListener, groupId: String) = runOnMain {
        studentsNavigationUseCases.navigateToGroupScreen(studentsNavigationListener, groupId)
    }

    fun loadData(studentId: String?) = runOnIOWithLoading {
        loadGroups(studentId)
    }

    fun openMenuGroupPopUp(group: Group) {
        println(group)
        onGroupChanged(group)
        menuStateHandler.closeMenu()
        _menuGroupPopUpVisible.value = true
    }

    fun closeMenuGroupPopUp() {
        _menuGroupPopUpVisible.value = false
        menuStateHandler.openMenu()
    }

    fun openDeletePopUp() {
        _menuGroupPopUpVisible.value = false
        _deletePopUpVisible.value = true
    }

    fun closeDeletePopUp() {
        _deletePopUpVisible.value = false
        menuStateHandler.openMenu()
    }

    fun openArchivePopUp() {
        _menuGroupPopUpVisible.value = false
        _archivePopUpVisible.value = true
    }

    fun closeArchivePopUp() {
        _archivePopUpVisible.value = false
        menuStateHandler.openMenu()
    }

    fun moveToArchive() = runOnIOWithLoading {
        closeArchivePopUp()
        checkGroupForNullable { group ->
            safeCall { groupMoveToArchiveUseCase.moveToArchive(group) }.processingLoading { newGroup ->
                removeGroupFromActiveGroups(group)
                addGroupToArchiveGroups(newGroup)
            }
        }
    }

    fun deleteGroup() = runOnIOWithLoading {
        closeDeletePopUp()
        checkGroupForNullable { group ->
            safeCall { deleteGroupUseCase.deleteGroup(groupId = group.id) }.processingLoading {
                removeGroupFromArchiveGroups(group)
            }
        }
    }

    fun bringItBackFromArchive() = runOnIOWithLoading {
        closeMenuGroupPopUp()
        checkGroupForNullable { group ->
            safeCall { groupBringBackFromArchiveUseCase.bringItBackFromArchive(group) }.processingLoading { newGroup ->
                removeGroupFromArchiveGroups(group)
                addGroupToActiveGroups(newGroup)
            }
        }
    }

    private fun loadGroups(studentId: String?) = runOnIO {
        safeCall { getGroupsUseCase.getGroups(studentId, ::onFirebaseLoading, ::onDatabaseLoading) }.processing()
    }

    private fun onFirebaseLoading(groups: Pair<List<Group>, List<Group>>) {
        stopLoading()
        onActiveGroupChanged(groups = groups.first)
        onArchiveGroupsChanged(groups = groups.second)
    }

    private fun onDatabaseLoading(groups: Pair<List<Group>, List<Group>>) {
        if (groups.first.isNotEmpty() || groups.second.isNotEmpty()) {
            onFirebaseLoading(groups)
        }
    }

    private fun checkGroupForNullable(f: suspend (Group) -> Unit) = runOnIO {
        safeCall { _group.value ?: throw GroupIsNotLoadingException() }.processing { group ->
            f(group)
        }
    }

    private fun removeGroupFromActiveGroups(group: Group) {
        _activeGroups.value = _activeGroups.value.toMutableList().apply {
            remove(group)
        }
    }

    private fun removeGroupFromArchiveGroups(group: Group) {
        _archiveGroups.value = _archiveGroups.value.toMutableList().apply {
            remove(group)
        }
    }

    private fun addGroupToActiveGroups(group: Group) = runOnIO {
        _activeGroups.value = _activeGroups.value.toMutableList().apply {
            add(group)
        }
    }

    private fun addGroupToArchiveGroups(group: Group) = runOnIO {
        _archiveGroups.value = _archiveGroups.value.toMutableList().apply {
            add(group)
        }
    }

    override val errorMap: Map<Int, Int>
        get() = mapOf(
            GROUP_IS_NOT_LOADING_EXCEPTION_CODE to R.string.group_is_not_loading_exception_message
        )
}