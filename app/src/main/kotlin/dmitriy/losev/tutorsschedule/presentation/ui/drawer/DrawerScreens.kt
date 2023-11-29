package dmitriy.losev.tutorsschedule.presentation.ui.drawer

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Money
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.PermIdentity
import androidx.compose.ui.graphics.vector.ImageVector
import dmitriy.losev.tutorsschedule.presentation.navigation.Screens

enum class DrawerScreens(
    val route: String,
    val title: String,
    val image: ImageVector,
    val icon: ImageVector? = null,
) {
    CALENDER_SCREEN(
        route = Screens.CalendarScreen.route,
        title = "Расписание",
        image = Icons.Default.CalendarMonth
    ),
    STUDENTS_SCREEN(
        route = Screens.StudentsScreen.route,
        title = "Ученики",
        image = Icons.Default.People
    ),
    FINANCE_SCREEN(
        route = Screens.FinanceScreen.route,
        title = "Финансы",
        image = Icons.Default.Money
    ),
    PROFILE_SCREEN(
        route = Screens.ProfileScreen.route,
        title = "Профиль",
        image = Icons.Default.PermIdentity,
        icon = Icons.Default.Edit
    );
}