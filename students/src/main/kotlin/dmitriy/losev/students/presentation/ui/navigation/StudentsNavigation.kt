package dmitriy.losev.students.presentation.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import dmitriy.losev.students.core.StudentsNavigationListener
import dmitriy.losev.students.presentation.ui.screens.MainStudentsScreen
import dmitriy.losev.students.presentation.ui.screens.contact.AddContactScreen
import dmitriy.losev.students.presentation.ui.screens.contact.ContactScreen
import dmitriy.losev.students.presentation.ui.screens.contact.UpdateContactScreen
import dmitriy.losev.students.presentation.ui.screens.students.AddStudentScreen
import dmitriy.losev.students.presentation.ui.screens.students.StudentScreen
import dmitriy.losev.students.presentation.ui.screens.students.UpdateStudentScreen

fun NavGraphBuilder.studentsNavigation(
    studentsNavigationListener: StudentsNavigationListener,
    route: String,
    startDestination: String = StudentsScreens.StudentsScreen.route
) {
    navigation(
        startDestination = startDestination,
        route = route
    ) {

        composable(
            route = StudentsScreens.StudentsScreen.route
        ) {
            MainStudentsScreen(studentsNavigationListener)
            //StudentsAndGroupsScreen(studentsNavigationListener)
        }

        composable(
            route = StudentsScreens.AddStudentScreen.route
        ) {
            AddStudentScreen(studentsNavigationListener)
        }

        composable(
            route = StudentsScreens.UpdateStudentScreen.route,
            arguments = StudentsScreens.UpdateStudentScreen.arguments
        ) { backStackEntry ->
            val studentId = backStackEntry.arguments?.getString(StudentsScreens.UpdateStudentScreen.STUDENT_ID)
            requireNotNull(studentId) { "studentId parameter wasn't found. Please make sure it's set!" }
            UpdateStudentScreen(studentId, studentsNavigationListener)
        }

        composable(
            route = StudentsScreens.StudentScreen.route,
            arguments = StudentsScreens.StudentScreen.arguments
        ) { backStackEntry ->
            val studentId = backStackEntry.arguments?.getString(StudentsScreens.StudentScreen.STUDENT_ID)
            requireNotNull(studentId) { "studentId parameter wasn't found. Please make sure it's set!" }
            StudentScreen(studentId, studentsNavigationListener)
        }

        composable(
            route = StudentsScreens.ContactScreen.route,
            arguments = StudentsScreens.ContactScreen.arguments
        ) { backStackEntry ->
            val studentId = backStackEntry.arguments?.getString(StudentsScreens.UpdateContactScreen.STUDENT_ID)
            val contactId = backStackEntry.arguments?.getString(StudentsScreens.UpdateContactScreen.CONTACT_ID)
            requireNotNull(studentId) { "studentId parameter wasn't found. Please make sure it's set!" }
            requireNotNull(contactId) { "contactId parameter wasn't found. Please make sure it's set!" }
            ContactScreen(studentId, contactId, studentsNavigationListener)
        }

        composable(
            route = StudentsScreens.AddContactScreen.route,
            arguments = StudentsScreens.AddContactScreen.arguments
        ) { backStackEntry ->
            val studentId = backStackEntry.arguments?.getString(StudentsScreens.AddContactScreen.STUDENT_ID)
            requireNotNull(studentId) { "studentId parameter wasn't found. Please make sure it's set!" }
            AddContactScreen(studentId, studentsNavigationListener)
        }

        composable(
            route = StudentsScreens.UpdateContactScreen.route,
            arguments = StudentsScreens.UpdateContactScreen.arguments
        ) { backStackEntry ->
            val studentId = backStackEntry.arguments?.getString(StudentsScreens.UpdateContactScreen.STUDENT_ID)
            val contactId = backStackEntry.arguments?.getString(StudentsScreens.UpdateContactScreen.CONTACT_ID)
            requireNotNull(studentId) { "studentId parameter wasn't found. Please make sure it's set!" }
            requireNotNull(contactId) { "contactId parameter wasn't found. Please make sure it's set!" }
            UpdateContactScreen(studentId, contactId, studentsNavigationListener)
        }
    }
}