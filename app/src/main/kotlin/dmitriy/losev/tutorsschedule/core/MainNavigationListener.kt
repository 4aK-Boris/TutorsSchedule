package dmitriy.losev.tutorsschedule.core

interface MainNavigationListener {

    suspend fun navigateToProfileScreen()

    suspend fun navigateToStudentsScreen()
}