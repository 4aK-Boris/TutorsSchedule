package dmitriy.losev.students.domain.usecases

import dmitriy.losev.students.core.StudentsBaseUseCase
import dmitriy.losev.students.core.StudentsNavigationListener

class StudentsNavigationUseCases: StudentsBaseUseCase() {

    suspend fun navigateToStudentsScreen(studentsNavigationListener: StudentsNavigationListener) {
        studentsNavigationListener.navigateToStudentsScreen()
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

    suspend fun navigateToUpdateContactScreen(studentsNavigationListener: StudentsNavigationListener, studentId: String, contactId: String){
        studentsNavigationListener.navigateToUpdateContactScreen(studentId, contactId)
    }

    suspend fun navigateToAuthenticationScreen(studentsNavigationListener: StudentsNavigationListener) {
        studentsNavigationListener.navigateToAuthenticationScreen()
    }

    suspend fun back(studentsNavigationListener: StudentsNavigationListener) {
        studentsNavigationListener.back()
    }
}