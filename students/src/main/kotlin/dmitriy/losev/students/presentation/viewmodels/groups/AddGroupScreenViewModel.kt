package dmitriy.losev.students.presentation.viewmodels.groups

import dmitriy.losev.core.EMPTY_STRING
import dmitriy.losev.core.models.StudentName
import dmitriy.losev.students.R
import dmitriy.losev.students.core.StudentsHandler
import dmitriy.losev.students.core.StudentsNavigationListener
import dmitriy.losev.students.core.exception.EMPTY_NAME_EXCEPTION_CODE
import dmitriy.losev.students.domain.usecases.StudentsNavigationUseCases
import dmitriy.losev.students.domain.usecases.groups.AddGroupUseCase
import dmitriy.losev.students.domain.usecases.student.GetStudentNamesUseCase
import dmitriy.losev.ui.core.BaseViewModel
import dmitriy.losev.ui.core.runOnIO
import dmitriy.losev.ui.core.runOnIOWithLoading
import dmitriy.losev.ui.core.runOnMain
import dmitriy.losev.ui.views.textfields.TextFieldState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class AddGroupScreenViewModel(
    private val studentsHandler: StudentsHandler,
    private val studentsNavigationUseCases: StudentsNavigationUseCases,
    private val addGroupUseCase: AddGroupUseCase,
    private val getStudentNamesUseCase: GetStudentNamesUseCase
): BaseViewModel() {

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

    fun loadData() = runOnIOWithLoading {
        loadStudentNames(studentsHandler.studentIds)
    }

    fun back(studentsNavigationListener: StudentsNavigationListener) = runOnMain {
        studentsNavigationUseCases.back(studentsNavigationListener)
    }

    fun navigateToChooseStudentsScreen(studentsNavigationListener: StudentsNavigationListener) = runOnMain {
        studentsNavigationUseCases.navigateToChooseStudentsScreen(studentsNavigationListener)
    }

    fun saveChanges(studentsNavigationListener: StudentsNavigationListener) = runOnIOWithLoading {
        val name = _name.value
        safeCall { addGroupUseCase.addGroup(name, studentsHandler.studentIds) }.processingLoading {
            studentsHandler.clear()
            back(studentsNavigationListener)
        }
    }

    private fun loadStudentNames(studentIds: List<String>) = runOnIO {
        safeCall { getStudentNamesUseCase.getStudentsNames(studentIds, ::onFirebaseLoading, ::onDatabaseLoading) }.processing()
    }

    private fun onFirebaseLoading(students: List<StudentName>) {
        stopLoading()
        onStudentsChanged(students)
    }

    private fun onDatabaseLoading(students: List<StudentName>) {
        if (students.isNotEmpty()) {
            onFirebaseLoading(students)
        }
    }

    override val errorMap: Map<Int, Int>
        get() = mapOf(
            EMPTY_NAME_EXCEPTION_CODE to R.string.empty_name_exception_message
        )

    override val errorFun: Map<Int, () -> Unit>
        get() = mapOf(
            EMPTY_NAME_EXCEPTION_CODE to { _nameTextFieldState.value = TextFieldState.WARNING }
        )
}