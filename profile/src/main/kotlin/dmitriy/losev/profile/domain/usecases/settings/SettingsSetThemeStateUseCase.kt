package dmitriy.losev.profile.domain.usecases.settings

import dmitriy.losev.datastore.domain.usecases.bool.DataStoreSetBooleanUseCase
import dmitriy.losev.profile.core.ProfileBaseUseCase
import dmitriy.losev.ui.core.THEME_KEY

class SettingsSetThemeStateUseCase(private val dataStoreSetBooleanUseCase: DataStoreSetBooleanUseCase): ProfileBaseUseCase() {

    suspend fun setThemeState(themeState: Boolean) {
        return dataStoreSetBooleanUseCase.setBooleanValue(key = THEME_KEY, value = themeState)
    }
}