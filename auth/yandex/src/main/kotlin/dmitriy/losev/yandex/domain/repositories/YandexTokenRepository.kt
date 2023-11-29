package dmitriy.losev.yandex.domain.repositories

import dmitriy.losev.firebase.domain.models.FirebaseToken
import dmitriy.losev.yandex.domain.models.YandexToken

interface YandexTokenRepository {

    suspend fun createToken(yandexToken: YandexToken): FirebaseToken
}