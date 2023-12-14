package dmitriy.losev.firebase.domain.repositories.periods

import dmitriy.losev.firebase.domain.models.Period

interface FirebasePeriodsRepository {

    suspend fun addPeriod(period: Period): String?

    suspend fun getPeriod(periodId: String): Period?

    suspend fun deletePeriod(periodId: String)

    suspend fun updatePeriod(periodId: String, period: Period)
}