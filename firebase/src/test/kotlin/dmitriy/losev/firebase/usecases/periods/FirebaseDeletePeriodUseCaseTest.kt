package dmitriy.losev.firebase.usecases.periods

import dmitriy.losev.firebase.domain.repositories.periods.FirebasePeriodsRepository
import dmitriy.losev.firebase.domain.usecases.periods.FirebaseDeletePeriodUseCase
import io.mockk.coVerifySequence
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test

class FirebaseDeletePeriodUseCaseTest {

    private val firebasePeriodsRepository = mockk<FirebasePeriodsRepository>(relaxed = true)

    private val firebaseDeletePeriodUseCase = FirebaseDeletePeriodUseCase(firebasePeriodsRepository)

    @Test
    fun testDeletePeriod(): Unit = runBlocking {

        firebaseDeletePeriodUseCase.deletePeriod(PERIOD_ID)

        coVerifySequence {
            firebasePeriodsRepository.deletePeriod(PERIOD_ID)
        }
    }

    companion object {
        private const val PERIOD_ID = "period_id"
    }
}