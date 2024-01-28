package dmitriy.losev.firebase.domain.usecases.periods

import dmitriy.losev.firebase.core.FirebaseBaseUseCase
import dmitriy.losev.firebase.core.exception.PeriodAddException
import dmitriy.losev.firebase.domain.models.Period
import dmitriy.losev.firebase.domain.repositories.periods.FirebasePeriodsRepository

class FirebaseAddPeriodUseCase(private val firebasePeriodsRepository: FirebasePeriodsRepository): FirebaseBaseUseCase() {

    suspend fun addPeriod(period: Period): String {
        return firebasePeriodsRepository.addPeriod(period = period) ?: throw PeriodAddException()
    }
}