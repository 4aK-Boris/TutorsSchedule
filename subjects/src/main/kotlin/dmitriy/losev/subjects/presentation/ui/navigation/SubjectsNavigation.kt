package dmitriy.losev.subjects.presentation.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import dmitriy.losev.subjects.core.SubjectsNavigationListener
import dmitriy.losev.subjects.presentation.ui.screens.AddSubjectScreen
import dmitriy.losev.subjects.presentation.ui.screens.EditSubjectScreen
import dmitriy.losev.subjects.presentation.ui.screens.SubjectsScreen

fun NavGraphBuilder.subjectsNavigation(
    subjectsNavigationListener: SubjectsNavigationListener,
    route: String,
    startDestination: String = SubjectsScreens.SubjectsScreen.route
) {

    navigation(
        startDestination = startDestination,
        route = route
    ) {

        composable(
            route = SubjectsScreens.SubjectsScreen.route
        ) {
            SubjectsScreen(subjectsNavigationListener)
        }

        composable(
            route = SubjectsScreens.AddSubjectScreen.route
        ) {
            AddSubjectScreen(subjectsNavigationListener)
        }

        composable(
            route = SubjectsScreens.EditSubjectScreen.route,
            arguments = SubjectsScreens.EditSubjectScreen.arguments
        ) { backStackEntry ->
            val subjectId = backStackEntry.arguments?.getString(SubjectsScreens.EditSubjectScreen.SUBJECT_ID)
            requireNotNull(subjectId) { "subjectId parameter wasn't found. Please make sure it's set!" }
            EditSubjectScreen(subjectsNavigationListener, subjectId)
        }
    }
}