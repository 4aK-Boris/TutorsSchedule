package dmitriy.losev.profile.data.network

import dmitriy.losev.network.BASE_URL
import dmitriy.losev.network.KtorClient
import dmitriy.losev.profile.data.dto.UserAvailabilityDTO

class ProfileNetworkImpl(
    private val client: KtorClient
): ProfileNetwork {

    override suspend fun checkUserAvailability(email: String): UserAvailabilityDTO {
        return client.get(url = BASE_URL + CHECK_USER_AVAILABILITY, params = mapOf(EMAIL_PARAMETER to email))
    }

    companion object {
        private const val EMAIL_PARAMETER = "email"
        private const val CHECK_USER_AVAILABILITY = "/user/available"
    }
}