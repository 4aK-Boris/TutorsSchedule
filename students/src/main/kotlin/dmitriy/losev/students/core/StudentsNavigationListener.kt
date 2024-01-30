package dmitriy.losev.students.core

interface StudentsNavigationListener {

    suspend fun navigateToStudentsAndGroupsScreen(isStudents: Boolean, studentId: String?, groupId: String?)

    suspend fun navigateToStudentScreen(studentId: String)

    suspend fun navigateToUpdateStudentScreen(studentId: String)

    suspend fun navigateToAddStudentScreen()

    suspend fun navigateToContactScreen(studentId: String, contactId: String)

    suspend fun navigateToAddContactScreen(studentId: String)

    suspend fun navigateToUpdateContactScreen(studentId: String, contactId: String)

    suspend fun navigateToAddGroupScreen()

    suspend fun navigateToUpdateGroupScreen(groupId: String)

    suspend fun navigateToGroupScreen(groupId: String)

    suspend fun navigateToChooseStudentsScreen(groupId: String?)

    suspend fun navigateToAuthenticationScreen()

    suspend fun back()
}