package dmitriy.losev.firebase.usecases.lessons.periods

import dmitriy.losev.firebase.core.usecases.lessons.BaseLessonPeriodsUseCaseTest
import dmitriy.losev.firebase.domain.usecases.lessons.periods.FirebaseAddLessonPeriodUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.koin.test.inject
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class FirebaseAddLessonPeriodUseCaseTest: BaseLessonPeriodsUseCaseTest() {

    private val firebaseAddLessonPeriodUseCase by inject<FirebaseAddLessonPeriodUseCase>()

    override suspend fun setUp() {
        logIn()
    }

    override suspend fun tearDown() {
        deletePeriods()
        logOut()
    }

    @Test
    fun testAddPeriod(): Unit = runBlocking {

        firebaseAddLessonPeriodUseCase.addPeriod(LESSON_ID, PERIOD_ID)

        val hasPeriod = hasPeriod()

        assertTrue(hasPeriod)

        val actualResult = getPeriod()

        assertEquals(PERIOD_ID, actualResult)
    }
}