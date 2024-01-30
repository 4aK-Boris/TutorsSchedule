package dmitriy.losev.students.presentation.viewmodels.groups

import dmitriy.losev.core.EMPTY_STRING
import dmitriy.losev.core.models.Group
import dmitriy.losev.core.models.StudentName
import dmitriy.losev.students.R
import dmitriy.losev.students.core.StudentsNavigationListener
import dmitriy.losev.students.core.exception.GROUP_IS_NOT_LOADING_EXCEPTION_CODE
import dmitriy.losev.students.domain.usecases.StudentsNavigationUseCases
import dmitriy.losev.students.domain.usecases.groups.GetGroupStudentNamesUseCase
import dmitriy.losev.students.domain.usecases.groups.GetGroupUseCase
import dmitriy.losev.ui.core.BaseViewModel
import dmitriy.losev.ui.core.runOnIO
import dmitriy.losev.ui.core.runOnIOWithLoading
import dmitriy.losev.ui.core.runOnMain
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class GroupScreenViewModel(
    private val studentsNavigationUseCases: StudentsNavigationUseCases,
    private val getGroupUseCase: GetGroupUseCase,
    private val getGroupStudentNamesUseCase: GetGroupStudentNamesUseCase,
) : BaseViewModel() {

    private val _name = MutableStateFlow(value = EMPTY_STRING)
    private val _students = MutableStateFlow(value = emptyList<StudentName>())

    val name = _name.asStateFlow()
    val students = _students.asStateFlow()

    private fun onNameChanged(name: String) {
        _name.value = name
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

    fun navigateToUpdateGroupScreen(studentsNavigationListener: StudentsNavigationListener, groupId: String) = runOnMain {
        studentsNavigationUseCases.navigateToUpdateGroupScreen(studentsNavigationListener, groupId)
    }

    fun navigateToChooseStudentsScreen(studentsNavigationListener: StudentsNavigationListener, groupId: String) = runOnMain {
        studentsNavigationUseCases.navigateToChooseStudentsScreen(studentsNavigationListener, groupId)
    }

    fun navigateToStudentsAndGroupsScreen(studentsNavigationListener: StudentsNavigationListener, groupId: String) = runOnMain {
        studentsNavigationUseCases.navigateToStudentsAndGroupsScreen(studentsNavigationListener, isStudents = true, groupId = groupId)
    }

    private fun loadGroup(groupId: String) = runOnIO {
        safeCall { getGroupUseCase.getGroup(groupId, ::onFirebaseGroupLoading, ::onDatabaseGroupLoading) }.processing()
    }

    private fun loadStudentNames(groupId: String) = runOnIO {
        safeCall { getGroupStudentNamesUseCase.getGroupStudentNames(groupId, ::onFirebaseStudentNamesLoading, ::onDatabaseStudentNamesLoading) }.processing()
    }

    private fun onFirebaseGroupLoading(group: Group) {
        stopLoading()
        onNameChanged(name = group.name)
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

    override val errorMap: Map<Int, Int>
        get() = mapOf(
            GROUP_IS_NOT_LOADING_EXCEPTION_CODE to R.string.group_is_not_loading_exception_message
        )
}