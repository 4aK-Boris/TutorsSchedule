package dmitriy.losev.students.presentation.viewmodels.groups

import dmitriy.losev.core.models.StudentName
import dmitriy.losev.students.R
import dmitriy.losev.students.core.StudentsHandler
import dmitriy.losev.students.core.StudentsNavigationListener
import dmitriy.losev.students.core.exception.EMPTY_STUDENTS_EXCEPTION_CODE
import dmitriy.losev.students.domain.usecases.StudentsNavigationUseCases
import dmitriy.losev.students.domain.usecases.groups.AddGroupStudentsUseCase
import dmitriy.losev.students.domain.usecases.groups.GetGroupStudentIdsUseCase
import dmitriy.losev.students.domain.usecases.student.GetStudentNamesUseCase
import dmitriy.losev.ui.core.BaseViewModel
import dmitriy.losev.ui.core.runOnIO
import dmitriy.losev.ui.core.runOnIOWithLoading
import dmitriy.losev.ui.core.runOnMain
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine

class ChooseStudentsScreenViewModel(
    private val studentsHandler: StudentsHandler,
    private val studentsNavigationUseCases: StudentsNavigationUseCases,
    private val getStudentNamesUseCase: GetStudentNamesUseCase,
    private val getGroupStudentIdsUseCase: GetGroupStudentIdsUseCase,
    private val addGroupStudentsUseCase: AddGroupStudentsUseCase
) : BaseViewModel() {

    private var groupStudents = emptyList<String>()

    private val _students = MutableStateFlow(value = emptyList<StudentName>())
    private val _selectedStudentIds = MutableStateFlow(value = emptySet<String>())

    val students = combine(_students, _selectedStudentIds) { students, selectedStudentIds ->
        students.map { student -> student to (student.id in selectedStudentIds) }.sortedByDescending { student -> student.second }
    }

    fun onSelectedStudentIdsChanged(studentId: String) {
        println(studentId)
        if (studentId in _selectedStudentIds.value) {
            removeSelectedStudent(studentId)
        } else {
            addSelectedStudent(studentId)
        }
    }

    fun onSelectedStudentIdsChanged(selectedStudentIds: List<String>) {
        _selectedStudentIds.value = selectedStudentIds.toMutableSet()
        groupStudents = selectedStudentIds
    }

    fun loadData(groupId: String?) = runOnIOWithLoading {
        onSelectedStudentIdsChanged(selectedStudentIds = studentsHandler.studentIds)
        loadStudents()
        groupId?.let { loadGroupStudentIds(groupId) }

    }

    fun back(studentsNavigationListener: StudentsNavigationListener) = runOnMain {
        studentsNavigationUseCases.back(studentsNavigationListener)
    }

    fun navigateGroupScreen(studentsNavigationListener: StudentsNavigationListener, groupId: String?) = runOnIO {
        if (groupId == null) {
            studentsHandler.studentIds = _selectedStudentIds.value.toList()
            back(studentsNavigationListener)
        } else {
            startLoading()
            safeCall {
                addGroupStudentsUseCase.addGroupStudents(
                    groupId = groupId,
                    oldStudentIds = groupStudents,
                    newStudentIds = _selectedStudentIds.value.toList()
                )
            }.processingLoading {
                back(studentsNavigationListener)
            }
        }
    }

    private fun loadGroupStudentIds(groupId: String) = runOnIO {
        safeCall { getGroupStudentIdsUseCase.getGroupStudentIds(groupId, ::onGroupStudentIdsFirebaseLoading, ::onGroupStudentIdsDatabaseLoading) }.processing()
    }

    private fun loadStudents() = runOnIO {
        safeCall { getStudentNamesUseCase.getStudentsNames(::onStudentsFirebaseLoading, ::onStudentsDatabaseLoading) }.processing()
    }

    private fun onGroupStudentIdsFirebaseLoading(studentIds: List<String>) {
        onSelectedStudentIdsChanged(studentIds)
    }

    private fun onGroupStudentIdsDatabaseLoading(studentIds: List<String>) {
        if (studentIds.isNotEmpty()) {
            onGroupStudentIdsFirebaseLoading(studentIds)
        }
    }

    private fun onStudentsFirebaseLoading(students: List<StudentName>) {
        stopLoading()
        _students.value = students
    }

    private fun onStudentsDatabaseLoading(students: List<StudentName>) {
        if (students.isNotEmpty()) {
            onStudentsFirebaseLoading(students)
        }
    }

    private fun removeSelectedStudent(studentId: String) {
        _selectedStudentIds.value = _selectedStudentIds.value.toMutableSet().apply {
            remove(studentId)
        }
    }

    private fun addSelectedStudent(studentId: String) {
        _selectedStudentIds.value = _selectedStudentIds.value.toMutableSet().apply {
            add(studentId)
        }
    }

    override val errorMap: Map<Int, Int>
        get() = mapOf(
            EMPTY_STUDENTS_EXCEPTION_CODE to R.string.empty_students_exception_message
        )
}