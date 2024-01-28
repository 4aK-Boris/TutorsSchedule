package dmitriy.losev.subjects.presentation.ui.navigation

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument

sealed interface SubjectsScreens {

    val name: String

    val route: String

    val arguments: List<NamedNavArgument>
        get() = emptyList()

    data object SubjectsScreen: SubjectsScreens {

        override val name = "subjects_screen"

        override val route = name
    }

    data object AddSubjectScreen: SubjectsScreens {

        override val name = "add_subject_screen"

        override val route = name
    }

    data object EditSubjectScreen: SubjectsScreens {

        const val SUBJECT_ID = "subject_id"

        override val name = "edit_subject_screen"

        override val route = "$name/$SUBJECT_ID={$SUBJECT_ID}"

        override val arguments = listOf(
            navArgument(name = SUBJECT_ID) {
                type = NavType.StringType
                defaultValue = ""
            }
        )

        fun createRoute(subjectId: String): String {
            return "$name/${SUBJECT_ID}=$subjectId"
        }
    }
}
