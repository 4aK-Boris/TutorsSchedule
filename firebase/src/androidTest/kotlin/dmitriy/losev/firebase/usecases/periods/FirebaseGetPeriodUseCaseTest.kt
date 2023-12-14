package dmitriy.losev.firebase.usecases.periods

import dmitriy.losev.firebase.core.usecases.periods.BasePeriodsUseCaseTest
import dmitriy.losev.firebase.domain.usecases.periods.FirebaseGetPeriodUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.koin.test.inject
import kotlin.test.assertTrue

class FirebaseGetPeriodUseCaseTest: BasePeriodsUseCaseTest() {

    private val firebaseGetPeriodUseCase by inject<FirebaseGetPeriodUseCase>()

    override suspend fun setUp() {
        logIn()
        addPeriod()
    }

    override suspend fun tearDown() {
        deletePeriods()
        logOut()
    }

    @Test
    fun testGetPeriod(): Unit = runBlocking {

        val hasPeriod =  hasPeriod()

        assertTrue(hasPeriod)

        val actualResult = firebaseGetPeriodUseCase.getPeriod(PERIOD_ID)

        equalsPeriods(period, actualResult)
    }
}