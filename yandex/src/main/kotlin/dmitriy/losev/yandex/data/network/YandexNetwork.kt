package dmitriy.losev.yandex.data.network

import dmitriy.losev.yandex.data.dto.YandexUserDataDTO

interface YandexNetwork {

    suspend fun getUserData(token: String): YandexUserDataDTO
}