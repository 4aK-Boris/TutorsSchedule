package dmitriy.losev.tutorsschedule.presentation.navigation

import androidx.navigation.NamedNavArgument

sealed interface Screens {

    val name: String

    val route: String

    val arguments: List<NamedNavArgument>
        get() = emptyList()

    data object AuthenticationScreen: Screens {

        override val name = "authentication_screen"

        override val route = name
    }

    data object CalendarScreen: Screens {

        override val name = "calendar_screen"

        override val route = name
    }

    data object StudentsScreen: Screens {

        override val name = "students_screen"

        override val route = name
    }

    data object FinanceScreen: Screens {

        override val name = "finance_screen"

        override val route = name
    }

    data object ProfileScreen: Screens {

        override val name = "profile_screen"

        override val route = name
    }
}
