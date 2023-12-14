package dmitriy.losev.firebase.repositories.periods

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.firebase.database.DatabaseReference
import dmitriy.losev.firebase.core.BaseRepositoryTest
import dmitriy.losev.firebase.core.PERIODS
import dmitriy.losev.firebase.data.dto.PeriodDTO
import dmitriy.losev.firebase.data.mappers.PeriodMapper
import dmitriy.losev.firebase.domain.models.Period
import dmitriy.losev.firebase.domain.models.types.DayOfWeek
import dmitriy.losev.firebase.domain.models.types.PeriodType
import dmitriy.losev.firebase.domain.repositories.periods.FirebasePeriodsRepository
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.tasks.await
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.test.inject
import java.time.ZonedDateTime
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

@RunWith(AndroidJUnit4::class)
class FirebasePeriodsRepositoryTest : BaseRepositoryTest() {

    private val reference by inject<DatabaseReference>()
    private val periodMapper by inject<PeriodMapper>()

    private val periodsReference by lazy { reference.child(PERIODS) }

    private val firebasePeriodsRepository by inject<FirebasePeriodsRepository>()

    override suspend fun tearDown() {
        deletePeriods()
    }

    @Test
    fun testAddPeriod(): Unit = runBlocking {

        val key = firebasePeriodsRepository.addPeriod(period)!!

        val hasPeriod = hasPeriod()

        assertTrue(hasPeriod)

        val actualResult = getPeriod(key)

        equalsPeriods(period.copy(id = key), actualResult)
    }

    @Test
    fun testUpdatePeriod(): Unit = runBlocking {

        addPeriod()

        val hasPeriod = hasPeriod()

        assertTrue(hasPeriod)

        val newPeriod = period.copy(type = NEW_PERIOD_TYPE, dayOfWeek = NEW_DAY_OF_WEEK)

        firebasePeriodsRepository.updatePeriod(PERIOD_ID, newPeriod)

        val actualResult = getPeriod()

        equalsPeriods(newPeriod, actualResult)
    }

    @Test
    fun testDeletePeriod(): Unit = runBlocking {

        addPeriod()

        var hasPeriod = hasPeriod()

        assertTrue(hasPeriod)

        firebasePeriodsRepository.deletePeriod(PERIOD_ID)

        hasPeriod = hasPeriod()

        assertFalse(hasPeriod)
    }

    @Test
    fun testGetPeriod(): Unit = runBlocking {

        addPeriod()

        val hasPeriod = hasPeriod()

        assertTrue(hasPeriod)

        val actualResult = firebasePeriodsRepository.getPeriod(PERIOD_ID)

        equalsPeriods(period, actualResult)
    }


    private fun equalsPeriods(expectedTask: Period, actualTask: Period?) {
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

    private suspend fun addPeriod() {
        addPeriod(id = PERIOD_ID)
    }

    private suspend fun addPeriod(id: String) {
        periodsReference.child(id).setValue(periodMapper.map(value = period.copy(id = id))).await()
    }

    private suspend fun deletePeriods() {
        periodsReference.get().await().children.forEach { dataSnapShot ->
            dataSnapShot.ref.removeValue().await()
        }
    }

    private suspend fun getPeriod(): Period? {
        return getPeriod(key = PERIOD_ID)
    }

    private suspend fun getPeriod(key: String): Period? {
        return periodsReference.child(key).get().await().getValue(PeriodDTO::class.java)?.let { periodDTO ->
            periodMapper.map(value = periodDTO)
        }
    }

    private suspend fun hasPeriod(): Boolean {
        return periodsReference.get().await().children.toList().isNotEmpty()
    }

    companion object {

        private const val PERIOD_ID = "4324324324324234v8324324324v32"
        private const val LESSON_ID = "dc9483284m032489832948m3298492"

        private val PERIOD_TYPE = PeriodType.WEEK
        private val DAY_OF_WEEK = DayOfWeek.MONDAY

        private val NEW_PERIOD_TYPE = PeriodType.TWO_WEEK
        private val NEW_DAY_OF_WEEK = DayOfWeek.SATURDAY

        private val dateTime = ZonedDateTime.now()

        private val period = Period(
            id = PERIOD_ID,
            lessonId = LESSON_ID,
            type = PERIOD_TYPE,
            dayOfWeek = DAY_OF_WEEK,
            firstDateTime = dateTime
        )
    }
} 