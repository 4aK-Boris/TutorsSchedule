package dmitriy.losev.students.presentation.ui.navigation

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument
import dmitriy.losev.core.EMPTY_STRING

sealed interface StudentsScreens {

    val name: String

    val route: String

    val arguments: List<NamedNavArgument>
        get() = emptyList()

    data object StudentsAndGroupsScreen: StudentsScreens {

        const val IS_STUDENTS = "is_students"
        const val STUDENT_ID = "student_id"
        const val GROUP_ID = "group_id"

        override val name = "students_and_groups_screen"

        override val route = "$name/{$IS_STUDENTS}/?$STUDENT_ID={$STUDENT_ID}/?$GROUP_ID={$GROUP_ID}"

        override val arguments = listOf(
            navArgument(name = IS_STUDENTS) {
                type = NavType.BoolType
                nullable = false
                defaultValue = true
            },
            navArgument(name = STUDENT_ID) {
                type = NavType.StringType
                nullable = true
                defaultValue = null
            },
            navArgument(name = GROUP_ID) {
                type = NavType.StringType
                nullable = true
                defaultValue = null
            }
        )

        fun createRoute(isStudents: Boolean, studentId: String?, groupId: String?): String {
            return "$name/$isStudents/?$STUDENT_ID=$studentId/?$GROUP_ID=$groupId"
        }
    }

    data object AddStudentScreen: StudentsScreens {

        override val name = "add_student_screen"

        override val route = name
    }

    data object ChooseStudentsScreen: StudentsScreens {

        const val GROUP_ID = "group_id"

        override val name = "choose_students_screen"

        override val route = "$name?$GROUP_ID={$GROUP_ID}"

        override val arguments = listOf(
            navArgument(name = GROUP_ID) {
                type = NavType.StringType
                nullable = true
                defaultValue = EMPTY_STRING
            }
        )

        fun createRoute(groupId: String?): String {
            return "$name?$GROUP_ID=$groupId"
        }
    }

    data object GroupScreen: StudentsScreens {

        const val GROUP_ID = "group_id"

        override val name = "group_screen"

        override val route = "$name/{$GROUP_ID}"

        override val arguments = listOf(
            navArgument(name = GROUP_ID) { type = NavType.StringType }
        )

        fun createRoute(groupId: String): String {
            return "$name/$groupId"
        }
    }

    data object AddGroupScreen: StudentsScreens {

        override val name = "add_group_screen"

        override val route = name
    }

    data object UpdateGroupScreen: StudentsScreens {

        const val GROUP_ID = "group_id"

        override val name = "update_group_screen"

        override val route = "$name/{$GROUP_ID}"

        override val arguments = listOf(
            navArgument(name = GROUP_ID) { type = NavType.StringType }
        )

        fun createRoute(groupId: String): String {
            return "$name/$groupId"
        }
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
