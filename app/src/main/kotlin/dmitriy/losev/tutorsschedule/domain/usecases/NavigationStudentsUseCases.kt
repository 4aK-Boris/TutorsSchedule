package dmitriy.losev.tutorsschedule.domain.usecases

import androidx.navigation.NavController
import dmitriy.losev.core.core.switchOnMain
import dmitriy.losev.students.presentation.ui.navigation.StudentsScreens

class NavigationStudentsUseCases {

    suspend fun navigateToStudentsScreen(navController: NavController) = switchOnMain {
        navController.navigate(StudentsScreens.StudentsScreen.route)
    }

    suspend fun navigateToUpdateStudentScreen(navController: NavController, studentId: String) = switchOnMain {
        navController.navigate(StudentsScreens.UpdateStudentsScreen.createRoute(studentId))
    }

    suspend fun navigateToAddStudentScreen(navController: NavController)  = switchOnMain {
        navController.navigate(StudentsScreens.AddStudentsScreen.route)
    }
}