package dmitriy.losev.firebase.data.repositories.periods

import com.google.firebase.database.DatabaseReference
import dmitriy.losev.firebase.core.PERIODS
import dmitriy.losev.firebase.data.dto.PeriodDTO
import dmitriy.losev.firebase.data.mappers.PeriodMapper
import dmitriy.losev.firebase.domain.models.Period
import dmitriy.losev.firebase.domain.repositories.periods.FirebasePeriodsRepository
import kotlinx.coroutines.tasks.await

class FirebasePeriodsRepositoryImpl(
    reference: DatabaseReference,
    private val periodMapper: PeriodMapper
): FirebasePeriodsRepository {

    private val periods by lazy { reference.child(PERIODS) }

    override suspend fun addPeriod(period: Period): String? {
        val periodDTO = periodMapper.map(value = period)
        periods.push().apply {
            setValue(periodDTO.copy(id = key)).await()
            return key
        }
    }

    override suspend fun getPeriod(periodId: String): Period? {
        return periods.child(periodId).get().await().getValue(PeriodDTO::class.java)?.let { periodDTO ->
            periodMapper.map(value = periodDTO)
        }
    }

    override suspend fun deletePeriod(periodId: String) {
        periods.child(periodId).removeValue().await()
    }

    override suspend fun updatePeriod(periodId: String, period: Period) {
        periods.updateChildren(mapOf(periodId to periodMapper.map(value = period))).await()
    }
}