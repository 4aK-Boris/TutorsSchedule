package dmitriy.losev.firebase.domain.usecases.periods

import dmitriy.losev.firebase.core.FirebaseBaseUseCase
import dmitriy.losev.firebase.domain.repositories.periods.FirebasePeriodsRepository

class FirebaseDeletePeriodUseCase(private val firebasePeriodsRepository: FirebasePeriodsRepository) : FirebaseBaseUseCase() {

    suspend fun deletePeriod(periodId: String) {
        return firebasePeriodsRepository.deletePeriod(periodId = periodId)
    }
}