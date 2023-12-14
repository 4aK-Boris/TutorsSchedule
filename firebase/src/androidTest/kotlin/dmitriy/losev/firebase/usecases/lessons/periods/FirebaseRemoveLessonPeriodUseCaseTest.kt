package dmitriy.losev.firebase.usecases.lessons.periods

import dmitriy.losev.firebase.core.usecases.lessons.BaseLessonPeriodsUseCaseTest
import dmitriy.losev.firebase.domain.usecases.lessons.periods.FirebaseRemoveLessonPeriodUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.koin.test.inject
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class FirebaseRemoveLessonPeriodUseCaseTest: BaseLessonPeriodsUseCaseTest() {

    private val firebaseRemoveLessonPeriodUseCase by inject<FirebaseRemoveLessonPeriodUseCase>()

    override suspend fun setUp() {
        logIn()
    }

    override suspend fun tearDown() {
        deletePeriods()
        logOut()
    }

    @Test
    fun testRemovePeriod(): Unit = runBlocking {

        addPeriod()

        var hasPeriod = hasPeriod()

        assertTrue(hasPeriod)

        firebaseRemoveLessonPeriodUseCase.removePeriod(LESSON_ID, PERIOD_ID)

        hasPeriod = hasPeriod()

        assertFalse(hasPeriod)
    }
}