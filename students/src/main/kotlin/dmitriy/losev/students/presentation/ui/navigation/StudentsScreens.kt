package dmitriy.losev.students.presentation.ui.navigation

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument

sealed interface StudentsScreens {

    val name: String

    val route: String

    val arguments: List<NamedNavArgument>
        get() = emptyList()

    data object StudentsScreen: StudentsScreens {

        override val name = "main_students_screen"

        override val route = name
    }

    data object AddStudentScreen: StudentsScreens {

        override val name = "add_student_screen"

        override val route = name
    }

    data object UpdateStudentScreen: StudentsScreens {

        const val STUDENT_ID = "student_id"

        override val name = "update_student_screen"

        override val route = "$name/{$STUDENT_ID}"

        override val arguments = listOf(
            navArgument(name = STUDENT_ID) { type = NavType.StringType }
        )

        fun createRoute(studentId: String): String {
            return "$name/$studentId"
        }
    }

    data object StudentScreen: StudentsScreens {

        const val STUDENT_ID = "student_id"

        override val name = "student_screen"

        override val route = "$name/{$STUDENT_ID}"

        override val arguments = listOf(
            navArgument(name = STUDENT_ID) { type = NavType.StringType }
        )

        fun createRoute(studentId: String): String {
            return "$name/$studentId"
        }
    }

    data object ContactScreen: StudentsScreens {

        const val STUDENT_ID = "student_id"
        const val CONTACT_ID = "contact_id"

        override val name = "contact_screen"

        override val route = "$name/{$STUDENT_ID}/{$CONTACT_ID}"

        override val arguments = listOf(
            navArgument(name = STUDENT_ID) { type = NavType.StringType },
            navArgument(name = CONTACT_ID) { type = NavType.StringType }
        )

        fun createRoute(studentId: String, contactId: String): String {
            return "$name/$studentId/$contactId"
        }
    }

    data object AddContactScreen: StudentsScreens {

        const val STUDENT_ID = "student_id"

        override val name = "add_contact_screen"

        override val route = "$name/{$STUDENT_ID}"

        override val arguments = listOf(
            navArgument(name = STUDENT_ID) { type = NavType.StringType }
        )

        fun createRoute(studentId: String): String {
            return "$name/$studentId"
        }
    }

    data object UpdateContactScreen: StudentsScreens {

        const val STUDENT_ID = "student_id"
        const val CONTACT_ID = "contact_id"

        override val name = "update_contact_screen"

        override val route = "$name/{$STUDENT_ID}/{$CONTACT_ID}"

        override val arguments = listOf(
            navArgument(name = STUDENT_ID) { type = NavType.StringType },
            navArgument(name = CONTACT_ID) { type = NavType.StringType }
        )

        fun createRoute(studentId: String, contactId: String): String {
            return "$name/$studentId/$contactId"
        }
    }
}
