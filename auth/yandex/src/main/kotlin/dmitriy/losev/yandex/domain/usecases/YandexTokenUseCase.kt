package dmitriy.losev.yandex.domain.usecases

import dmitriy.losev.core.core.ErrorHandler
import dmitriy.losev.core.core.result.Result
import dmitriy.losev.firebase.domain.models.FirebaseToken
import dmitriy.losev.yandex.core.YandexBaseUseCase
import dmitriy.losev.yandex.domain.models.YandexToken
import dmitriy.losev.yandex.domain.repositories.YandexTokenRepository

class YandexTokenUseCase(
    errorHandler: ErrorHandler,
    private val yandexTokenRepository: YandexTokenRepository
): YandexBaseUseCase(errorHandler)  {

    suspend fun createToken(yandexToken: YandexToken): Result<FirebaseToken> = safeCall {
        yandexTokenRepository.createToken(yandexToken)
    }
}