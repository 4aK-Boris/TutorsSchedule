package dmitriy.losev.tutorsschedule.domain.usecases

import androidx.navigation.NavController
import dmitriy.losev.core.BaseUseCase
import dmitriy.losev.core.switchOnMain

class NavigationUseCases : BaseUseCase() {

    suspend fun back(navController: NavController): Unit = switchOnMain {
        navController.popBackStack()
    }

    suspend fun clearScreen(navController: NavController, count: Int = 50): Unit = switchOnMain {
        repeat(count) {
            back(navController)
        }
    }

    suspend fun removeScreen(navController: NavController, route: String) = switchOnMain {
        navController.clearBackStack(route)
    }
}