package dmitriy.losev.firebase.usecases.periods

import dmitriy.losev.firebase.domain.models.Period
import dmitriy.losev.firebase.domain.repositories.periods.FirebasePeriodsRepository
import dmitriy.losev.firebase.domain.usecases.periods.FirebaseAddPeriodUseCase
import io.mockk.coVerifySequence
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test

class FirebaseAddPeriodUseCaseTest {

    private val period = mockk<Period>()

    private val firebasePeriodsRepository = mockk<FirebasePeriodsRepository>(relaxed = true)

    private val firebaseAddPeriodUseCase = FirebaseAddPeriodUseCase(firebasePeriodsRepository)

    @Test
    fun testAddPeriod(): Unit = runBlocking {

        firebaseAddPeriodUseCase.addPeriod(period)

        coVerifySequence {
            firebasePeriodsRepository.addPeriod(any())
        }
    }
}