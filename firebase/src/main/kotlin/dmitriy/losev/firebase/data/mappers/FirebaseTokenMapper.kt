package dmitriy.losev.firebase.data.mappers

import dmitriy.losev.firebase.data.dto.FirebaseTokenDTO
import dmitriy.losev.firebase.domain.models.FirebaseToken

class FirebaseTokenMapper {

    fun map(value: FirebaseTokenDTO): FirebaseToken {
        return FirebaseToken(token = value.token)
    }
}