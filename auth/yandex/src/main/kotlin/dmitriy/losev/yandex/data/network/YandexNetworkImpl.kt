package dmitriy.losev.yandex.data.network

import dmitriy.losev.firebase.data.dto.FirebaseTokenDTO
import dmitriy.losev.network.BASE_URL
import dmitriy.losev.network.KtorClient
import dmitriy.losev.yandex.data.dto.TokenDTO

class YandexNetworkImpl(private val client: KtorClient): YandexNetwork {

    override suspend fun getFirebaseToken(token: TokenDTO): FirebaseTokenDTO {
        return client.post(url = BASE_URL + YANDEX_FIREBASE_TOKEN, body = token)
    }

    companion object {
        private const val YANDEX_FIREBASE_TOKEN = "/token/yandex"
    }
}