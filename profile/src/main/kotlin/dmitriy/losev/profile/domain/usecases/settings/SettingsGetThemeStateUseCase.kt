package dmitriy.losev.profile.domain.usecases.settings

import dmitriy.losev.datastore.domain.usecases.bool.DataStoreGetBooleanUseCase
import dmitriy.losev.profile.core.ProfileBaseUseCase
import dmitriy.losev.ui.core.THEME_DEFAULT_VALUE
import dmitriy.losev.ui.core.THEME_KEY
import kotlinx.coroutines.flow.Flow

class SettingsGetThemeStateUseCase(private val dataStoreGetBooleanUseCase: DataStoreGetBooleanUseCase): ProfileBaseUseCase() {

    suspend fun getThemeState(): Flow<Boolean> {
        return dataStoreGetBooleanUseCase.getBooleanValue(key = THEME_KEY, defaultValue = THEME_DEFAULT_VALUE)
    }
}