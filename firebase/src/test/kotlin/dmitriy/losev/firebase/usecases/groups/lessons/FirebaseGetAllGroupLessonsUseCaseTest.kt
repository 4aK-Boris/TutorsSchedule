package dmitriy.losev.firebase.usecases.groups.lessons

import dmitriy.losev.firebase.domain.repositories.groups.FirebaseGroupLessonsRepository
import dmitriy.losev.firebase.domain.usecases.groups.lessons.FirebaseGetAllGroupLessonsUseCase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import kotlin.test.assertContentEquals

class FirebaseGetAllGroupLessonsUseCaseTest {

    private val firebaseGroupLessonsRepository = mockk<FirebaseGroupLessonsRepository>()

    private val firebaseGetAllGroupLessonsUseCase = FirebaseGetAllGroupLessonsUseCase(firebaseGroupLessonsRepository)

    @Test
    fun testGetAllLessons(): Unit = runBlocking {

        val lessons = listOf(LESSON_ID, LESSON_ID, LESSON_ID)

        coEvery { firebaseGroupLessonsRepository.getAllLessons(GROUP_ID) } returns lessons

        val actualResult = firebaseGetAllGroupLessonsUseCase.getAllLessons(GROUP_ID)

        coVerify { firebaseGroupLessonsRepository.getAllLessons(GROUP_ID) }

        assertContentEquals(lessons, actualResult)
    }

    companion object {

        private const val GROUP_ID = "group_id"
        private const val LESSON_ID = "lesson_id"
    }
}