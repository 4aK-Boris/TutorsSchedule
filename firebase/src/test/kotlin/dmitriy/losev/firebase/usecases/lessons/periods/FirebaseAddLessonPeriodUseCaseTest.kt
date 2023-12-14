package dmitriy.losev.firebase.usecases.lessons.periods

import dmitriy.losev.firebase.domain.repositories.lessons.FirebaseLessonPeriodsRepository
import dmitriy.losev.firebase.domain.usecases.lessons.periods.FirebaseAddLessonPeriodUseCase
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test

class FirebaseAddLessonPeriodUseCaseTest {

    private val firebaseLessonPeriodsRepository = mockk<FirebaseLessonPeriodsRepository>(relaxed = true)

    private val firebaseAddLessonPeriodUseCase = FirebaseAddLessonPeriodUseCase(firebaseLessonPeriodsRepository)

    @Test
    fun testAddPeriod(): Unit = runBlocking {

        firebaseAddLessonPeriodUseCase.addPeriod(LESSON_ID, PERIOD_ID)

        coVerify { firebaseLessonPeriodsRepository.addPeriod(LESSON_ID, PERIOD_ID) }
    }

    companion object {
        private const val LESSON_ID = "lesson_id"
        private const val PERIOD_ID = "period_id"
    }
}