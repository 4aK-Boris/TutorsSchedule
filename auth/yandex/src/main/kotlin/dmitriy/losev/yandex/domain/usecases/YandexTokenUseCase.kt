package dmitriy.losev.yandex.domain.usecases

import dmitriy.losev.firebase.domain.models.FirebaseToken
import dmitriy.losev.yandex.core.YandexBaseUseCase
import dmitriy.losev.yandex.domain.models.YandexToken
import dmitriy.losev.yandex.domain.repositories.YandexTokenRepository

class YandexTokenUseCase(private val yandexTokenRepository: YandexTokenRepository): YandexBaseUseCase()  {

    suspend fun createToken(yandexToken: YandexToken): FirebaseToken {
        return yandexTokenRepository.createToken(yandexToken)
    }
}