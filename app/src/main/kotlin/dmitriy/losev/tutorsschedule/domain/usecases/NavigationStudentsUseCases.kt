package dmitriy.losev.tutorsschedule.domain.usecases

import androidx.navigation.NavController
import dmitriy.losev.core.switchOnMain
import dmitriy.losev.students.presentation.ui.navigation.StudentsScreens

class NavigationStudentsUseCases {

    suspend fun navigateToStudentsScreen(navController: NavController): Unit = switchOnMain {
        navController.navigate(StudentsScreens.StudentsScreen.route)
    }

    suspend fun navigateToStudentScreen(navController: NavController, studentId: String): Unit = switchOnMain {
        navController.navigate(StudentsScreens.StudentScreen.createRoute(studentId))
    }

    suspend fun navigateToUpdateStudentScreen(navController: NavController, studentId: String): Unit = switchOnMain {
        navController.navigate(StudentsScreens.UpdateStudentScreen.createRoute(studentId))
    }

    suspend fun navigateToAddStudentScreen(navController: NavController): Unit = switchOnMain {
        navController.navigate(StudentsScreens.AddStudentScreen.route)
    }

    suspend fun navigateToContactScreen(navController: NavController, studentId: String, contactId: String): Unit = switchOnMain {
        navController.navigate(StudentsScreens.ContactScreen.createRoute(studentId, contactId))
    }

    suspend fun navigateToAddContactScreen(navController: NavController, studentId: String): Unit = switchOnMain {
        navController.navigate(StudentsScreens.AddContactScreen.createRoute(studentId))
    }

    suspend fun navigateToUpdateContactScreen(navController: NavController, studentId: String, contactId: String): Unit = switchOnMain {
        navController.navigate(StudentsScreens.UpdateContactScreen.createRoute(studentId, contactId))
    }
}