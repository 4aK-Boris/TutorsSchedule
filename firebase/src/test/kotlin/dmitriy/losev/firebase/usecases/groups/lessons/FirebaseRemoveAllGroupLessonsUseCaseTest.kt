package dmitriy.losev.firebase.usecases.groups.lessons

import dmitriy.losev.firebase.domain.repositories.groups.FirebaseGroupLessonsRepository
import dmitriy.losev.firebase.domain.usecases.groups.lessons.FirebaseRemoveAllGroupLessonsUseCase
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test

class FirebaseRemoveAllGroupLessonsUseCaseTest {

    private val firebaseGroupLessonsRepository = mockk<FirebaseGroupLessonsRepository>(relaxed = true)

    private val firebaseRemoveAllGroupLessonsUseCase = FirebaseRemoveAllGroupLessonsUseCase(firebaseGroupLessonsRepository)

    @Test
    fun testRemoveAllLessons(): Unit = runBlocking {

        firebaseRemoveAllGroupLessonsUseCase.removeAllLessons(GROUP_ID)

        coVerify { firebaseGroupLessonsRepository.removeAllLessons(GROUP_ID) }
    }

    companion object {
        private const val GROUP_ID = "group_id"
    }
}