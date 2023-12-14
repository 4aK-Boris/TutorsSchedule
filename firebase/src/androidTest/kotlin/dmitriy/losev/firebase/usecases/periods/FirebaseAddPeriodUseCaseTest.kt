package dmitriy.losev.firebase.usecases.periods

import dmitriy.losev.firebase.core.usecases.periods.BasePeriodsUseCaseTest
import dmitriy.losev.firebase.domain.usecases.periods.FirebaseAddPeriodUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.koin.test.inject
import kotlin.test.assertTrue

class FirebaseAddPeriodUseCaseTest: BasePeriodsUseCaseTest() {

    private val firebaseAddPeriodUseCase by inject<FirebaseAddPeriodUseCase>()

    override suspend fun setUp() {
        logIn()
    }

    override suspend fun tearDown() {
        deletePeriods()
        logOut()
    }

    @Test
    fun testAddPeriod(): Unit = runBlocking {

        val key = firebaseAddPeriodUseCase.addPeriod(period)

        val hasPeriod = hasPeriod()

        assertTrue(hasPeriod)

        val actualResult = getPeriod(key)

        equalsPeriods(period.copy(id = key), actualResult)
    }
}