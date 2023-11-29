package dmitriy.losev.auth.data.network

import dmitriy.losev.auth.data.dto.UserAbsenceDTO
import dmitriy.losev.auth.data.dto.UserAvailableDTO

interface AuthenticationNetwork {

    suspend fun checkUserAvailable(email: String): UserAvailableDTO

    suspend fun checkUserAbsence(email: String): UserAbsenceDTO
}