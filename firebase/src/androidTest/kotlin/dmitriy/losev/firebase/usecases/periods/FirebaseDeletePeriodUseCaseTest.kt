package dmitriy.losev.firebase.usecases.periods

import dmitriy.losev.firebase.core.usecases.periods.BasePeriodsUseCaseTest
import dmitriy.losev.firebase.domain.usecases.periods.FirebaseDeletePeriodUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.koin.test.inject
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class FirebaseDeletePeriodUseCaseTest: BasePeriodsUseCaseTest() {

    private val firebaseDeletePeriodUseCase by inject<FirebaseDeletePeriodUseCase>()

    override suspend fun setUp() {
        logIn()
        addPeriod()
    }

    override suspend fun tearDown() {
        logOut()
    }

    @Test
    fun testDeletePeriod(): Unit = runBlocking {

        var hasPeriod =  hasPeriod()

        assertTrue(hasPeriod)

        firebaseDeletePeriodUseCase.deletePeriod(PERIOD_ID)

        hasPeriod = hasPeriod()

        assertFalse(hasPeriod)
    }
}