package dmitriy.losev.firebase.usecases.lessons

import dmitriy.losev.firebase.domain.usecases.lessons.FirebaseDeleteFullLessonUseCase
import dmitriy.losev.firebase.domain.usecases.lessons.FirebaseDeleteLessonUseCase
import dmitriy.losev.firebase.domain.usecases.lessons.periods.FirebaseGetAllLessonPeriodsUseCase
import dmitriy.losev.firebase.domain.usecases.periods.FirebaseDeleteFullPeriodUseCase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test

class FirebaseDeleteFullLessonUseCaseTest {

    private val firebaseGetAllLessonPeriodsUseCase = mockk<FirebaseGetAllLessonPeriodsUseCase>()
    private val firebaseDeleteLessonUseCase = mockk<FirebaseDeleteLessonUseCase>(relaxed = true)
    private val firebaseDeleteFullPeriodUseCase = mockk<FirebaseDeleteFullPeriodUseCase>(relaxed = true)

    private val periods = listOf(PERIOD_ID, PERIOD_ID, PERIOD_ID)

    private val firebaseDeleteFullLessonUseCase = FirebaseDeleteFullLessonUseCase(
        firebaseGetAllLessonPeriodsUseCase,
        firebaseDeleteLessonUseCase,
        firebaseDeleteFullPeriodUseCase
    )

    @Test
    fun testDeleteFullLesson(): Unit = runBlocking {

        coEvery { firebaseGetAllLessonPeriodsUseCase.getAllPeriods(LESSON_ID) } returns periods

        firebaseDeleteFullLessonUseCase.deleteFullLesson(LESSON_ID)

        coVerify { firebaseGetAllLessonPeriodsUseCase.getAllPeriods(LESSON_ID) }
        coVerify(exactly = SIZE) { firebaseDeleteFullPeriodUseCase.deleteFullPeriod(PERIOD_ID) }
        coVerify { firebaseDeleteLessonUseCase.deleteLesson(LESSON_ID) }
    }

    companion object {

        private const val SIZE = 3

        private const val LESSON_ID = "lesson_id"
        private const val PERIOD_ID = "period_id"
    }
}