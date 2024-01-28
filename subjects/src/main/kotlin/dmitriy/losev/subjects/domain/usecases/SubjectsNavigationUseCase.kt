package dmitriy.losev.subjects.domain.usecases

import dmitriy.losev.subjects.core.SubjectsBaseUseCase
import dmitriy.losev.subjects.core.SubjectsNavigationListener

class SubjectsNavigationUseCase: SubjectsBaseUseCase() {

    suspend fun navigateToAddSubjectScreen(subjectsNavigationListener: SubjectsNavigationListener) {
        subjectsNavigationListener.navigateToAddSubjectScreen()
    }

    suspend fun navigateToEditSubjectScreen(subjectsNavigationListener: SubjectsNavigationListener, subjectId: String) {
        subjectsNavigationListener.navigateToEditSubjectScreen(subjectId)
    }

    suspend fun navigateToProfileScreen(subjectsNavigationListener: SubjectsNavigationListener) {
        subjectsNavigationListener.navigateToProfileScreen()
    }

    suspend fun back(subjectsNavigationListener: SubjectsNavigationListener) {
        subjectsNavigationListener.back()
    }
}