package dmitriy.losev.firebase.usecases.periods

import dmitriy.losev.firebase.core.usecases.periods.BasePeriodsUseCaseTest
import dmitriy.losev.core.models.types.DayOfWeek
import dmitriy.losev.core.models.types.PeriodType
import dmitriy.losev.firebase.domain.usecases.periods.FirebaseUpdatePeriodUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.koin.test.inject
import kotlin.test.assertTrue

class FirebaseUpdatePeriodUseCaseTest: BasePeriodsUseCaseTest() {

    private val firebaseUpdatePeriodUseCase by inject<FirebaseUpdatePeriodUseCase>()

    override suspend fun setUp() {
        logIn()
        addPeriod()
    }

    override suspend fun tearDown() {
        deletePeriods()
        logOut()
    }

    @Test
    fun testUpdatePeriod(): Unit = runBlocking {

        val hasPeriod =  hasPeriod()

        assertTrue(hasPeriod)

        val newPeriod = period.copy(type = PERIOD_TYPE, dayOfWeek = DAY_OF_WEEK)

        firebaseUpdatePeriodUseCase.updatePeriod(PERIOD_ID, newPeriod)

        val actualResult = getPeriod()

        equalsPeriods(newPeriod, actualResult)
    }

    companion object {
        private val PERIOD_TYPE = PeriodType.TWO_WEEK
        private val DAY_OF_WEEK = DayOfWeek.SATURDAY
    }
}