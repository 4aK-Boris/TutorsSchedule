package dmitriy.losev.ui.usecases

import dmitriy.losev.datastore.domain.usecases.bool.DataStoreGetBooleanUseCase
import dmitriy.losev.ui.core.CoreUiBaseUseCase
import dmitriy.losev.ui.core.THEME_DEFAULT_VALUE
import dmitriy.losev.ui.core.THEME_KEY
import kotlinx.coroutines.flow.Flow

class GetThemeStateUseCase(private val dataStoreGetBooleanUseCase: DataStoreGetBooleanUseCase): CoreUiBaseUseCase() {

    suspend fun getThemeState(): Flow<Boolean> {
        return dataStoreGetBooleanUseCase.getBooleanValue(key = THEME_KEY, defaultValue = THEME_DEFAULT_VALUE)
    }
}