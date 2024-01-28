package dmitriy.losev.firebase.usecases.lessons.periods

import dmitriy.losev.firebase.domain.repositories.lessons.FirebaseLessonPeriodsRepository
import dmitriy.losev.firebase.domain.usecases.lessons.periods.FirebaseRemoveAllLessonPeriodsUseCase
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test

class FirebaseRemoveAllLessonPeriodsUseCaseTest {

    private val firebaseLessonPeriodsRepository = mockk<FirebaseLessonPeriodsRepository>(relaxed = true)

    private val firebaseRemoveAllLessonPeriodsUseCase = FirebaseRemoveAllLessonPeriodsUseCase(firebaseLessonPeriodsRepository)

    @Test
    fun testRemoveAllPeriods(): Unit = runBlocking {

        firebaseRemoveAllLessonPeriodsUseCase.removeAllPeriods(LESSON_ID)

        coVerify { firebaseLessonPeriodsRepository.removeAllPeriods(LESSON_ID) }
    }

    companion object {
        private const val LESSON_ID = "lesson_id"
    }
}