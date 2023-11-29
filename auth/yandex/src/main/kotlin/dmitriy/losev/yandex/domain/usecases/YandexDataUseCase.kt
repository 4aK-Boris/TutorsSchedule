package dmitriy.losev.yandex.domain.usecases

import dmitriy.losev.core.core.ErrorHandler
import dmitriy.losev.yandex.core.YandexBaseUseCase
import dmitriy.losev.yandex.domain.models.YandexToken
import dmitriy.losev.yandex.domain.repositories.YandexDataRepository


class YandexDataUseCase(
    errorHandler: ErrorHandler,
    private val yandexDataRepository: YandexDataRepository
): YandexBaseUseCase(errorHandler)  {

    suspend fun getYandexUserData(yandexToken: YandexToken) = safeCall {
        yandexDataRepository.getYandexUserData(yandexToken)
    }
}