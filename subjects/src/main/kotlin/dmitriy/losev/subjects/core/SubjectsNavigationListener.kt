package dmitriy.losev.subjects.core

interface SubjectsNavigationListener {

    suspend fun navigateToAddSubjectScreen()

    suspend fun navigateToEditSubjectScreen(subjectId: String)

    suspend fun navigateToProfileScreen()

    suspend fun back()
}