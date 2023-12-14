package dmitriy.losev.firebase.usecases.periods

import dmitriy.losev.firebase.core.exception.NullablePeriodException
import dmitriy.losev.firebase.domain.models.Period
import dmitriy.losev.firebase.domain.repositories.periods.FirebasePeriodsRepository
import dmitriy.losev.firebase.domain.usecases.periods.FirebaseGetPeriodUseCase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class FirebaseGetPeriodUseCaseTest {

    private val period = mockk<Period>()

    private val firebasePeriodsRepository = mockk<FirebasePeriodsRepository>()

    private val firebaseGetPeriodUseCase = FirebaseGetPeriodUseCase(firebasePeriodsRepository)

    @Test
    fun testGetNotNullPeriod(): Unit = runBlocking {

        coEvery { firebasePeriodsRepository.getPeriod(periodId = PERIOD_ID) } returns period

        val actualResult = firebaseGetPeriodUseCase.getPeriod(PERIOD_ID)

        coVerify { firebasePeriodsRepository.getPeriod(periodId = PERIOD_ID) }

        assertEquals(period, actualResult)
    }

    @Test
    fun testGetNullablePeriod(): Unit = runBlocking {

        coEvery { firebasePeriodsRepository.getPeriod(periodId = PERIOD_ID) } returns null

        assertFailsWith(exceptionClass = NullablePeriodException::class) {
            firebaseGetPeriodUseCase.getPeriod(PERIOD_ID)
        }

        coVerify { firebasePeriodsRepository.getPeriod(periodId = PERIOD_ID) }
    }

    companion object {
        private const val PERIOD_ID = "period_id"
    }
}