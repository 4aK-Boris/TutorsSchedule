package dmitriy.losev.yandex.domain.repositories

import dmitriy.losev.yandex.domain.models.YandexToken
import dmitriy.losev.yandex.domain.models.YandexUserData

interface YandexDataRepository {

    suspend fun getYandexUserData(yandexToken: YandexToken): YandexUserData
}