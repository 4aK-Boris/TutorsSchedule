package dmitriy.losev.profile.domain.usecases.camera

import dmitriy.losev.profile.core.ProfileBaseUseCase
import java.time.LocalDateTime
import java.time.ZoneOffset

class CameraGetTimeUseCase: ProfileBaseUseCase() {

    fun getTime(): Long {
        return LocalDateTime.now().toEpochSecond(ZoneOffset.UTC)
    }
}