package dmitriy.losev.auth.data.network

import dmitriy.losev.auth.data.dto.UserAbsenceDTO
import dmitriy.losev.auth.data.dto.UserAvailableDTO
import dmitriy.losev.network.BASE_URL
import dmitriy.losev.network.KtorClient

class AuthenticationNetworkImpl(
    private val client: KtorClient
) : AuthenticationNetwork {

    override suspend fun checkUserAvailable(email: String): UserAvailableDTO {
        return client.get(
            url = BASE_URL + CHECK_USER_AVAILABLE,
            params = mapOf(EMAIL_PARAMETER to email)
        )
    }

    override suspend fun checkUserAbsence(email: String): UserAbsenceDTO {
        return client.get(
            url = BASE_URL + CHECK_USER_ABSENCE,
            params = mapOf(EMAIL_PARAMETER to email)
        )
    }

    companion object {
        private const val EMAIL_PARAMETER = "email"

        private const val CHECK_USER_AVAILABLE = "/user/available"
        private const val CHECK_USER_ABSENCE = "/user/absence"
    }
}