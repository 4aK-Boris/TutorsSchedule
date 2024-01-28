package dmitriy.losev.tutorsschedule.domain.usecases

import dmitriy.losev.core.BaseUseCase
import dmitriy.losev.tutorsschedule.core.MainNavigationListener

class MainNavigationUseCases: BaseUseCase() {

    suspend fun navigateToProfileScreen(mainNavigationListener: MainNavigationListener) {
        mainNavigationListener.navigateToProfileScreen()
    }

    suspend fun navigateToStudentsScreen(mainNavigationListener: MainNavigationListener) {
        mainNavigationListener.navigateToStudentsScreen()
    }
}