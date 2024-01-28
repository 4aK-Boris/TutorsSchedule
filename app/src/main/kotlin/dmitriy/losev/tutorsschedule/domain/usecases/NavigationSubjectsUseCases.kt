package dmitriy.losev.tutorsschedule.domain.usecases

import androidx.navigation.NavController
import dmitriy.losev.core.switchOnMain
import dmitriy.losev.subjects.presentation.ui.navigation.SubjectsScreens
import dmitriy.losev.tutorsschedule.core.AppBaseUseCase

class NavigationSubjectsUseCases: AppBaseUseCase() {

    suspend fun navigateToAddSubjectScreen(navController: NavController): Unit = switchOnMain {
        navController.navigate(route = SubjectsScreens.AddSubjectScreen.route) {
            launchSingleTop = true
            restoreState = true
        }
    }

    suspend fun navigateToEditSubjectScreen(navController: NavController, subjectId: String): Unit = switchOnMain {
        navController.navigate(route = SubjectsScreens.EditSubjectScreen.createRoute(subjectId)) {
            restoreState = true
        }
    }

    suspend fun navigateToSubjectsScreen(navController: NavController): Unit = switchOnMain {
        navController.navigate(route = SubjectsScreens.SubjectsScreen.route) {
            launchSingleTop = true
            restoreState = true
        }
    }
}