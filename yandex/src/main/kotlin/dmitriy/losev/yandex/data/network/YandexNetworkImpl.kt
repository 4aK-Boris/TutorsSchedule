package dmitriy.losev.yandex.data.network

import dmitriy.losev.network.KtorClient
import dmitriy.losev.yandex.data.dto.YandexUserDataDTO

class YandexNetworkImpl(
    private val ktorClient: KtorClient
) : YandexNetwork {

    override suspend fun getUserData(token: String): YandexUserDataDTO {
        return ktorClient.get(
            url = YANDEX_DATA_URL,
            params = mapOf("format" to "json", "jwt_secret" to "cfda7f7a1023416e946f554d1912e229"),
            headers = KtorClient.authHeader(token = token)
        )
    }

    companion object {
        private const val YANDEX_DATA_URL = "https://login.yandex.ru/info"
    }
}