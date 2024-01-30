package dmitriy.losev.students.domain.usecases

import dmitriy.losev.students.core.StudentsBaseUseCase
import dmitriy.losev.students.core.StudentsNavigationListener

class StudentsNavigationUseCases: StudentsBaseUseCase() {

    suspend fun navigateToStudentsAndGroupsScreen(
        studentsNavigationListener: StudentsNavigationListener,
        isStudents: Boolean = true,
        studentId: String? = null,
        groupId: String? = null
    ) {
        studentsNavigationListener.navigateToStudentsAndGroupsScreen(isStudents, studentId, groupId)
    }

    suspend fun navigateToStudentScreen(studentsNavigationListener: StudentsNavigationListener, studentId: String) {
        studentsNavigationListener.navigateToStudentScreen(studentId)
    }

    suspend fun navigateToUpdateStudentScreen(studentsNavigationListener: StudentsNavigationListener, studentId: String) {
        studentsNavigationListener.navigateToUpdateStudentScreen(studentId)
    }

    suspend fun navigateToAddStudentScreen(studentsNavigationListener: StudentsNavigationListener) {
        studentsNavigationListener.navigateToAddStudentScreen()
    }

    suspend fun navigateToContactScreen(studentsNavigationListener: StudentsNavigationListener, studentId: String, contactId: String){
        studentsNavigationListener.navigateToContactScreen(studentId, contactId)
    }

    suspend fun navigateToAddContactScreen(studentsNavigationListener: StudentsNavigationListener, studentId: String) {
        studentsNavigationListener.navigateToAddContactScreen(studentId)
    }

    suspend fun navigateToUpdateContactScreen(studentsNavigationListener: StudentsNavigationListener, studentId: String, contactId: String) {
        studentsNavigationListener.navigateToUpdateContactScreen(studentId, contactId)
    }

    suspend fun navigateToAddGroupScreen(studentsNavigationListener: StudentsNavigationListener) {
        studentsNavigationListener.navigateToAddGroupScreen()
    }

    suspend fun navigateToUpdateGroupScreen(studentsNavigationListener: StudentsNavigationListener, groupId: String) {
        studentsNavigationListener.navigateToUpdateGroupScreen(groupId)
    }

    suspend fun navigateToGroupScreen(studentsNavigationListener: StudentsNavigationListener, groupId: String) {
        studentsNavigationListener.navigateToGroupScreen(groupId)
    }

    suspend fun navigateToChooseStudentsScreen(studentsNavigationListener: StudentsNavigationListener, groupId: String? = null) {
        studentsNavigationListener.navigateToChooseStudentsScreen(groupId)
    }

    suspend fun navigateToAuthenticationScreen(studentsNavigationListener: StudentsNavigationListener) {
        studentsNavigationListener.navigateToAuthenticationScreen()
    }

    suspend fun back(studentsNavigationListener: StudentsNavigationListener) {
        studentsNavigationListener.back()
    }
}