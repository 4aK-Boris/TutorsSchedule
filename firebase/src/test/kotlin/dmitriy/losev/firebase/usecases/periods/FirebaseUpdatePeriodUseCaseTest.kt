package dmitriy.losev.firebase.usecases.periods

import dmitriy.losev.firebase.domain.models.Period
import dmitriy.losev.firebase.domain.repositories.periods.FirebasePeriodsRepository
import dmitriy.losev.firebase.domain.usecases.periods.FirebaseUpdatePeriodUseCase
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test

class FirebaseUpdatePeriodUseCaseTest {

    private val period = mockk<Period>()

    private val firebasePeriodsRepository = mockk<FirebasePeriodsRepository>(relaxed = true)

    private val firebaseUpdatePeriodUseCase = FirebaseUpdatePeriodUseCase(firebasePeriodsRepository)

    @Test
    fun testUpdatePeriod(): Unit = runBlocking {

        firebaseUpdatePeriodUseCase.updatePeriod(PERIOD_ID, period)

        coVerify { firebasePeriodsRepository.updatePeriod(PERIOD_ID, period) }
    }

    companion object {
        private const val PERIOD_ID = "period_id"
    }
}