package dmitriy.losev.firebase.usecases.lessons.periods

import dmitriy.losev.firebase.core.usecases.lessons.BaseLessonPeriodsUseCaseTest
import dmitriy.losev.firebase.domain.usecases.lessons.periods.FirebaseRemoveAllLessonPeriodsUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.koin.test.inject
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class FirebaseRemoveAllLessonPeriodsUseCaseTest: BaseLessonPeriodsUseCaseTest() {

    private val firebaseRemoveAllLessonPeriodsUseCase by inject<FirebaseRemoveAllLessonPeriodsUseCase>()

    override suspend fun setUp() {
        logIn()
    }

    override suspend fun tearDown() {
        deletePeriods()
        logOut()
    }

    @Test
    fun removeAllPeriods(): Unit = runBlocking {

        val count = 10

        addPeriods(count)

        var hasPeriods = hasPeriods()

        assertTrue(hasPeriods)

        firebaseRemoveAllLessonPeriodsUseCase.removeAllPeriods(LESSON_ID)

        hasPeriods = hasPeriods()

        assertFalse(hasPeriods)
    }
}