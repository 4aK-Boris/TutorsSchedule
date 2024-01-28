package dmitriy.losev.yandex.domain.usecases

import dmitriy.losev.yandex.core.YandexBaseUseCase
import dmitriy.losev.yandex.domain.models.YandexToken
import dmitriy.losev.yandex.domain.models.YandexUserData
import dmitriy.losev.yandex.domain.repositories.YandexDataRepository


class YandexDataUseCase(private val yandexDataRepository: YandexDataRepository): YandexBaseUseCase()  {

    suspend fun getYandexUserData(yandexToken: YandexToken): YandexUserData {
        return yandexDataRepository.getYandexUserData(yandexToken)
    }
}