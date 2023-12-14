package dmitriy.losev.firebase.usecases.lessons.periods

import dmitriy.losev.firebase.domain.repositories.lessons.FirebaseLessonPeriodsRepository
import dmitriy.losev.firebase.domain.usecases.lessons.periods.FirebaseGetAllLessonPeriodsUseCase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import kotlin.test.assertContentEquals

class FirebaseGetAllLessonPeriodsUseCaseTest {

    private val firebaseLessonPeriodsRepository = mockk<FirebaseLessonPeriodsRepository>()

    private val firebaseGetAllLessonPeriodsUseCase = FirebaseGetAllLessonPeriodsUseCase(firebaseLessonPeriodsRepository)

    @Test
    fun testGetAllPeriods(): Unit = runBlocking {

        val periods = listOf(PERIOD_ID, PERIOD_ID, PERIOD_ID)

        coEvery { firebaseLessonPeriodsRepository.getAllPeriods(LESSON_ID) } returns periods

        val actualResult = firebaseGetAllLessonPeriodsUseCase.getAllPeriods(LESSON_ID)

        coVerify { firebaseLessonPeriodsRepository.getAllPeriods(LESSON_ID) }

        assertContentEquals(periods, actualResult)
    }

    companion object {

        private const val LESSON_ID = "lesson_id"
        private const val PERIOD_ID = "period_id"
    }
}