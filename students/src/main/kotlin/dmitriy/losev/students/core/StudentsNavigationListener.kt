package dmitriy.losev.students.core

interface StudentsNavigationListener {

    suspend fun navigateToStudentsScreen()

    suspend fun navigateToStudentScreen(studentId: String)

    suspend fun navigateToUpdateStudentScreen(studentId: String)

    suspend fun navigateToAddStudentScreen()

    suspend fun navigateToContactScreen(studentId: String, contactId: String)

    suspend fun navigateToAddContactScreen(studentId: String)

    suspend fun navigateToUpdateContactScreen(studentId: String, contactId: String)

    suspend fun navigateToAuthenticationScreen()

    suspend fun back()
}