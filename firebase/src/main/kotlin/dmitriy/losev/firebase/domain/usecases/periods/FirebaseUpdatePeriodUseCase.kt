package dmitriy.losev.firebase.domain.usecases.periods

import dmitriy.losev.firebase.core.FirebaseBaseUseCase
import dmitriy.losev.firebase.domain.models.Period
import dmitriy.losev.firebase.domain.repositories.periods.FirebasePeriodsRepository

class FirebaseUpdatePeriodUseCase(private val firebasePeriodsRepository: FirebasePeriodsRepository): FirebaseBaseUseCase() {

    suspend fun updatePeriod(periodId: String, period: Period) {
        return firebasePeriodsRepository.updatePeriod(periodId = periodId, period = period)
    }
}