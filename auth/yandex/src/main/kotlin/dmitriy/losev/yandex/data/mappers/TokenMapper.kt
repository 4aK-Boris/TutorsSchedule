package dmitriy.losev.yandex.data.mappers

import dmitriy.losev.yandex.data.dto.TokenDTO
import dmitriy.losev.yandex.domain.models.YandexToken

class TokenMapper {

    fun map(value: YandexToken): TokenDTO {
        return TokenDTO(value.token)
    }

    fun map(value: TokenDTO): YandexToken {
        return YandexToken(value.token)
    }
}