package dmitriy.losev.profile.data.network

import dmitriy.losev.profile.data.dto.UserAbsenceDTO
import dmitriy.losev.profile.data.dto.UserAvailableDTO

interface ProfileNetwork {

    suspend fun checkUserAvailable(email: String): UserAvailableDTO

    suspend fun checkUserAbsence(email: String): UserAbsenceDTO

    suspend fun downloadAvatar(url: String): ByteArray?
}