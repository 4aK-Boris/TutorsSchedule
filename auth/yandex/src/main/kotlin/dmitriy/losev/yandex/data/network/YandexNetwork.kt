package dmitriy.losev.yandex.data.network

import dmitriy.losev.firebase.data.dto.FirebaseTokenDTO
import dmitriy.losev.yandex.data.dto.TokenDTO

interface YandexNetwork {

    suspend fun getFirebaseToken(token: TokenDTO): FirebaseTokenDTO
}