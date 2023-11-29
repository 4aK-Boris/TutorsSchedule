package dmitriy.losev.profile.data.network

import dmitriy.losev.profile.data.dto.UserAvailabilityDTO

interface ProfileNetwork {

    suspend fun checkUserAvailability(email: String): UserAvailabilityDTO
}