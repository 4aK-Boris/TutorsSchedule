package dmitriy.losev.firebase.core.usecases.periods

import com.google.firebase.database.DatabaseReference
import dmitriy.losev.firebase.core.BaseUseCaseTest
import dmitriy.losev.firebase.core.PERIODS
import dmitriy.losev.firebase.data.dto.PeriodDTO
import dmitriy.losev.firebase.data.mappers.PeriodMapper
import dmitriy.losev.firebase.domain.models.Period
import dmitriy.losev.firebase.domain.models.types.DayOfWeek
import dmitriy.losev.firebase.domain.models.types.PeriodType
import kotlinx.coroutines.tasks.await
import org.koin.test.inject
import java.time.ZonedDateTime
import kotlin.test.assertEquals

abstract class BasePeriodsUseCaseTest : BaseUseCaseTest() {

    private val reference by inject<DatabaseReference>()

    private val periodMapper by inject<PeriodMapper>()

    private val periodsReference by lazy { reference.child(PERIODS) }

    protected suspend fun addPeriod() {
        periodsReference.child(PERIOD_ID).setValue(periodMapper.map(value = period)).await()
    }

    protected suspend fun addPeriod(id: String) {
        periodsReference.child(id).setValue(periodMapper.map(value = period.copy(id = id))).await()
    }

    protected suspend fun deletePeriods() {
        periodsReference.get().await().children.forEach { period ->
            period.ref.removeValue().await()
        }
    }

    protected suspend fun getPeriod(): Period? {
        return periodsReference.child(PERIOD_ID).get().await().getValue(PeriodDTO::class.java)?.let { periodDTO ->
            periodMapper.map(value = periodDTO)
        }
    }

    protected suspend fun getPeriod(key: String): Period? {
        return periodsReference.child(key).get().await().getValue(PeriodDTO::class.java)?.let { periodDTO ->
            periodMapper.map(value = periodDTO)
        }
    }

    protected suspend fun hasPeriod(): Boolean {
        return periodsReference.get().await().children.toList().isNotEmpty()
    }

    protected fun equalsPeriods(expectedTask: Period, actualTask: Period?) {
        assertEquals(expectedTask.id, actualTask?.id)
        assertEquals(expectedTask.lessonId, actualTask?.lessonId)
        assertEquals(expectedTask.type, actualTask?.type)
        assertEquals(expectedTask.dayOfWeek, actualTask?.dayOfWeek)

        assertEquals(expectedTask.firstDateTime.year, actualTask?.firstDateTime?.year)
        assertEquals(expectedTask.firstDateTime.month, actualTask?.firstDateTime?.month)
        assertEquals(expectedTask.firstDateTime.dayOfMonth, actualTask?.firstDateTime?.dayOfMonth)
        assertEquals(expectedTask.firstDateTime.hour, actualTask?.firstDateTime?.hour)
        assertEquals(expectedTask.firstDateTime.minute, actualTask?.firstDateTime?.minute)
        assertEquals(expectedTask.firstDateTime.second, actualTask?.firstDateTime?.second)
    }

    companion object {

        const val PERIOD_ID = "4324324324324234v8324324324v32"
        private const val LESSON_ID = "dc9483284m032489832948m3298492"

        private val PERIOD_TYPE = PeriodType.WEEK
        private val DAY_OF_WEEK = DayOfWeek.MONDAY

        private val dateTime = ZonedDateTime.now()

        val period = Period(
            id = PERIOD_ID,
            lessonId = LESSON_ID,
            type = PERIOD_TYPE,
            dayOfWeek = DAY_OF_WEEK,
            firstDateTime = dateTime
        )
    }
}