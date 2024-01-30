package dmitriy.losev.students.presentation.viewmodels.groups

import dmitriy.losev.core.EMPTY_STRING
import dmitriy.losev.core.models.Group
import dmitriy.losev.core.models.StudentName
import dmitriy.losev.students.R
import dmitriy.losev.students.core.StudentsNavigationListener
import dmitriy.losev.students.core.exception.EMPTY_NAME_EXCEPTION_CODE
import dmitriy.losev.students.core.exception.GROUP_IS_NOT_LOADING_EXCEPTION_CODE
import dmitriy.losev.students.core.exception.GroupIsNotLoadingException
import dmitriy.losev.students.domain.usecases.StudentsNavigationUseCases
import dmitriy.losev.students.domain.usecases.groups.GetGroupStudentNamesUseCase
import dmitriy.losev.students.domain.usecases.groups.GetGroupUseCase
import dmitriy.losev.students.domain.usecases.groups.UpdateGroupUseCase
import dmitriy.losev.ui.core.BaseViewModel
import dmitriy.losev.ui.core.runOnIO
import dmitriy.losev.ui.core.runOnIOWithLoading
import dmitriy.losev.ui.core.runOnMain
import dmitriy.losev.ui.views.textfields.TextFieldState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class UpdateGroupScreenViewModel(
    private val studentsNavigationUseCases: StudentsNavigationUseCases,
    private val getGroupUseCase: GetGroupUseCase,
    private val getGroupStudentNamesUseCase: GetGroupStudentNamesUseCase,
    private val updateGroupUseCase: UpdateGroupUseCase
): BaseViewModel() {

    private var group: Group? = null

    private val _name = MutableStateFlow(value = EMPTY_STRING)
    private val _students = MutableStateFlow(value = emptyList<StudentName>())

    private val _nameTextFieldState = MutableStateFlow(value = TextFieldState.DEFAULT)

    val name = _name.asStateFlow()
    val students = _students.asStateFlow()

    val nameTextFieldState = _nameTextFieldState.asStateFlow()

    fun onNameChanged(name: String) {
        _name.value = name
    }

    fun clearNameTextFieldState() {
        _nameTextFieldState.value = TextFieldState.DEFAULT
    }

    private fun onStudentsChanged(students: List<StudentName>) {
        _students.value = students
    }

    fun loadData(groupId: String) = runOnIOWithLoading {
        loadGroup(groupId)
        loadStudentNames(groupId)
    }

    fun back(studentsNavigationListener: StudentsNavigationListener) = runOnMain {
        studentsNavigationUseCases.back(studentsNavigationListener)
    }

    fun navigateToChooseStudentsScreen(studentsNavigationListener: StudentsNavigationListener, groupId: String) = runOnMain {
        studentsNavigationUseCases.navigateToChooseStudentsScreen(studentsNavigationListener, groupId)
    }

    fun saveChanges(studentsNavigationListener: StudentsNavigationListener) = runOnIOWithLoading {
        checkGroupForNullableAndCopy { group ->
            safeCall { updateGroupUseCase.updateGroup(group) }.processingLoading {
                back(studentsNavigationListener)
            }
        }
    }

    private fun loadGroup(groupId: String) = runOnIO {
        safeCall { getGroupUseCase.getGroup(groupId, ::onFirebaseGroupLoading, ::onDatabaseGroupLoading) }.processing()
    }

    private fun loadStudentNames(groupId: String) = runOnIO {
        safeCall { getGroupStudentNamesUseCase.getGroupStudentNames(groupId, ::onFirebaseStudentNamesLoading, ::onDatabaseStudentNamesLoading) }.processing()
    }

    private fun onFirebaseGroupLoading(group: Group) {
        stopLoading()
        this.group = group
        if (_name.value.isBlank()) {
            onNameChanged(name = group.name)
        }
    }

    private fun onDatabaseGroupLoading(group: Group?) {
        group?.let { onFirebaseGroupLoading(group) }
    }

    private fun onFirebaseStudentNamesLoading(students: List<StudentName>) {
        onStudentsChanged(students)
    }

    private fun onDatabaseStudentNamesLoading(students: List<StudentName>) {
        if (students.isNotEmpty()) {
            onFirebaseStudentNamesLoading(students)
        }
    }

    private fun checkGroupForNullableAndCopy(f: suspend (Group) -> Unit) = runOnIO {
        safeCall { group ?: throw GroupIsNotLoadingException() }.processing { group ->
            f(group.copy(name = _name.value))
        }
    }

    override val errorMap: Map<Int, Int>
        get() = mapOf(
            EMPTY_NAME_EXCEPTION_CODE to R.string.empty_name_exception_message,
            GROUP_IS_NOT_LOADING_EXCEPTION_CODE to R.string.group_is_not_loading_exception_message
        )

    override val errorFun: Map<Int, () -> Unit>
        get() = mapOf(
            EMPTY_NAME_EXCEPTION_CODE to { _nameTextFieldState.value = TextFieldState.WARNING }
        )
}