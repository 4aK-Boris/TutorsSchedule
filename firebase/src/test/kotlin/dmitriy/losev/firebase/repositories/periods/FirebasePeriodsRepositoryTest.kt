package dmitriy.losev.firebase.repositories.periods

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseReference
import dmitriy.losev.firebase.core.FirebaseTask
import dmitriy.losev.firebase.core.PERIODS
import dmitriy.losev.firebase.data.dto.PeriodDTO
import dmitriy.losev.firebase.data.mappers.PeriodMapper
import dmitriy.losev.firebase.data.repositories.periods.FirebasePeriodsRepositoryImpl
import dmitriy.losev.firebase.domain.models.Period
import io.mockk.Called
import io.mockk.coVerifySequence
import io.mockk.every
import io.mockk.mockk
import io.mockk.unmockkAll
import io.mockk.verify
import io.mockk.verifySequence
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class FirebasePeriodsRepositoryTest {

    private val periodsReference = mockk<DatabaseReference>()
    private val periodReference = mockk<DatabaseReference>()
    private val period = mockk<Period>()
    private val periodDTO = mockk<PeriodDTO>()
    private val result = mockk<Void>()
    private val dataSnapshotResult = mockk<DataSnapshot>()

    private val reference = mockk<DatabaseReference>()
    private val periodMapper = mockk<PeriodMapper>()

    private val task = FirebaseTask(data = result)
    private val dataSnapshotTask = FirebaseTask(data = dataSnapshotResult)

    private val firebasePeriodsRepository = FirebasePeriodsRepositoryImpl(reference, periodMapper)

    @BeforeEach
    fun setUp() {
        every { reference.child(PERIODS) } returns periodsReference
        every { periodsReference.child(PERIOD_ID) } returns periodReference
        every { periodMapper.map(value = period) } returns periodDTO
        every { periodMapper.map(value = periodDTO) } returns period
    }

    @AfterEach
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun testAddPeriod(): Unit = runBlocking {

        every { periodDTO.copy(id = any()) } returns periodDTO
        every { periodsReference.push() } returns periodReference
        every { periodReference.key } returns KEY
        every { periodReference.setValue(periodDTO) } returns task

        firebasePeriodsRepository.addPeriod(period)

        verifySequence {
            periodMapper.map(value = period)
            reference.child(PERIODS)
            periodsReference.push()
            periodReference.key
            periodReference.setValue(periodDTO)
            periodReference.key
        }
    }

    @Test
    fun testGetNotNullPeriod(): Unit = runBlocking {

        every { periodReference.get() } returns dataSnapshotTask
        every { dataSnapshotResult.getValue(PeriodDTO::class.java) } returns periodDTO

        val actualResult = firebasePeriodsRepository.getPeriod(PERIOD_ID)

        coVerifySequence {
            reference.child(PERIODS)
            periodReference.get()
            dataSnapshotResult.getValue(PeriodDTO::class.java)
            periodMapper.map(value = periodDTO)
        }

        assertEquals(period, actualResult)
    }

    @Test
    fun testGetNullablePeriod(): Unit = runBlocking {

        every { periodReference.get() } returns dataSnapshotTask
        every { dataSnapshotResult.getValue(PeriodDTO::class.java) } returns null

        val actualResult = firebasePeriodsRepository.getPeriod(PERIOD_ID)

        coVerifySequence {
            reference.child(PERIODS)
            periodReference.get()
            dataSnapshotResult.getValue(PeriodDTO::class.java)
        }

        verify { periodMapper.map(value = periodDTO) wasNot Called }

        assertNull(actualResult)
    }

    @Test
    fun testUpdatePeriod(): Unit = runBlocking {

        every { periodsReference.updateChildren(mapOf(PERIOD_ID to periodDTO)) } returns task

        firebasePeriodsRepository.updatePeriod(PERIOD_ID, period)

        verifySequence {
            reference.child(PERIODS)
            periodMapper.map(value = period)
            periodsReference.updateChildren(mapOf(PERIOD_ID to periodDTO))
        }
    }

    @Test
    fun testDeletePeriod(): Unit = runBlocking {

        every { periodReference.removeValue() } returns task

        firebasePeriodsRepository.deletePeriod(PERIOD_ID)

        verifySequence {
            reference.child(PERIODS)
            periodReference.removeValue()
        }
    }

    companion object {
        private const val PERIOD_ID = "period_id"
        private const val KEY = "key"
    }
}