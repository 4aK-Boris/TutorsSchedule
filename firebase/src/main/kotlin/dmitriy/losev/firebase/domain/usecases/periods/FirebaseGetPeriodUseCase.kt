package dmitriy.losev.firebase.domain.usecases.periods

import dmitriy.losev.firebase.core.FirebaseBaseUseCase
import dmitriy.losev.firebase.core.exception.NullablePeriodException
import dmitriy.losev.firebase.domain.models.Period
import dmitriy.losev.firebase.domain.repositories.periods.FirebasePeriodsRepository

class FirebaseGetPeriodUseCase(private val firebasePeriodsRepository: FirebasePeriodsRepository): FirebaseBaseUseCase() {

    suspend fun getPeriod(periodId: String): Period {
        return firebasePeriodsRepository.getPeriod(periodId = periodId) ?: throw NullablePeriodException()
    }
}