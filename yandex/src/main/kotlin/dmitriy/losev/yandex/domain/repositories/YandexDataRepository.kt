package dmitriy.losev.yandex.domain.repositories

import dmitriy.losev.yandex.domain.models.YandexUserData

interface YandexDataRepository {

    suspend fun getUserData(token: String): YandexUserData

    suspend fun verifyToken(token: String): Boolean
}