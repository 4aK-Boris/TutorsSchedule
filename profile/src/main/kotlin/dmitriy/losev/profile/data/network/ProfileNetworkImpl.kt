package dmitriy.losev.profile.data.network

import dmitriy.losev.network.BASE_URL
import dmitriy.losev.network.KtorClient
import dmitriy.losev.network.KtorFileClient
import dmitriy.losev.profile.data.dto.UserAbsenceDTO
import dmitriy.losev.profile.data.dto.UserAvailableDTO

class ProfileNetworkImpl(
    private val client: KtorClient,
    private val fileClient: KtorFileClient
): ProfileNetwork {

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

    override suspend fun downloadAvatar(url: String): ByteArray? {
        return fileClient.getFile(url)
    }

    companion object {
        private const val EMAIL_PARAMETER = "email"

        private const val CHECK_USER_AVAILABLE = "/user/available"
        private const val CHECK_USER_ABSENCE = "/user/absence"
    }
}