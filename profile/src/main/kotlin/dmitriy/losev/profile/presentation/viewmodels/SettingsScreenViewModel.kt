package dmitriy.losev.profile.presentation.viewmodels

import dmitriy.losev.profile.core.ProfileNavigationListener
import dmitriy.losev.profile.domain.usecases.ProfileNavigationUseCases
import dmitriy.losev.profile.domain.usecases.settings.SettingsGetThemeStateUseCase
import dmitriy.losev.profile.domain.usecases.settings.SettingsSetThemeStateUseCase
import dmitriy.losev.ui.core.BaseViewModel
import dmitriy.losev.ui.core.runOnBackground
import dmitriy.losev.ui.core.runOnMain
import kotlinx.coroutines.flow.emptyFlow

class SettingsScreenViewModel(
    private val profileNavigationUseCases: ProfileNavigationUseCases,
    private val settingsSetThemeStateUseCase: SettingsSetThemeStateUseCase,
    private val settingsGetThemeStateUseCase: SettingsGetThemeStateUseCase
) : BaseViewModel() {

    var themeSettingsState = emptyFlow<Boolean>()

    fun onThemeStateChanged(themeState: Boolean) = runOnBackground {
        safeCall { settingsSetThemeStateUseCase.setThemeState(themeState) }.processing()
    }

    fun loadSettings() = runOnBackground {
        safeCall { settingsGetThemeStateUseCase.getThemeState() }.processing { themeFlow ->
            themeSettingsState = themeFlow
        }
    }

    fun back(profileNavigationListener: ProfileNavigationListener) = runOnMain {
        profileNavigationUseCases.back(profileNavigationListener)
    }
}