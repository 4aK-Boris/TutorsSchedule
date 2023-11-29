package dmitriy.losev.yandex.data.repositories

import dmitriy.losev.firebase.data.mappers.FirebaseTokenMapper
import dmitriy.losev.firebase.domain.models.FirebaseToken
import dmitriy.losev.yandex.data.mappers.TokenMapper
import dmitriy.losev.yandex.data.network.YandexNetwork
import dmitriy.losev.yandex.domain.models.YandexToken
import dmitriy.losev.yandex.domain.repositories.YandexTokenRepository

class YandexTokenRepositoryImpl(
    private val yandexNetwork: YandexNetwork,
    private val tokenMapper: TokenMapper,
    private val firebaseTokenMapper: FirebaseTokenMapper,
): YandexTokenRepository {

    override suspend fun createToken(yandexToken: YandexToken): FirebaseToken {
        val yandexTokenDTO = tokenMapper.map(value = yandexToken)
        val firebaseTokenDTO = yandexNetwork.getFirebaseToken(yandexTokenDTO)
        return firebaseTokenMapper.map(value = firebaseTokenDTO)
    }
}