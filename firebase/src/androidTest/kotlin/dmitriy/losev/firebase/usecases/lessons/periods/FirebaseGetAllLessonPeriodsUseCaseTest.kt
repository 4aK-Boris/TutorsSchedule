package dmitriy.losev.firebase.usecases.lessons.periods

import dmitriy.losev.firebase.core.usecases.lessons.BaseLessonPeriodsUseCaseTest
import dmitriy.losev.firebase.domain.usecases.lessons.periods.FirebaseGetAllLessonPeriodsUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.koin.test.inject
import kotlin.test.assertEquals

class FirebaseGetAllLessonPeriodsUseCaseTest: BaseLessonPeriodsUseCaseTest() {

    private val firebaseGetAllLessonPeriodsUseCase by inject<FirebaseGetAllLessonPeriodsUseCase>()

    override suspend fun setUp() {
        logIn()
    }

    override suspend fun tearDown() {
        deletePeriods()
        logOut()
    }

    @Test
    fun testGetAllPeriods(): Unit = runBlocking {

        val count = 10

        addPeriods(count = count)

        val actualResult = firebaseGetAllLessonPeriodsUseCase.getAllPeriods(LESSON_ID)

        assertEquals(count, actualResult.size)

        actualResult.forEachIndexed { index, periodId ->
            assertEquals("${PERIOD_ID}-$index", periodId)
        }
    }
}