package dmitriy.losev.firebase.usecases.groups.students

import dmitriy.losev.firebase.domain.repositories.groups.FirebaseGroupStudentsRepository
import dmitriy.losev.firebase.domain.usecases.groups.students.FirebaseRemoveAllGroupStudentsUseCase
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test

class FirebaseRemoveAllGroupStudentsUseCaseTest {

    private val firebaseGroupStudentsRepository = mockk<FirebaseGroupStudentsRepository>(relaxed = true)

    private val firebaseRemoveAllGroupStudentsUseCase = FirebaseRemoveAllGroupStudentsUseCase(firebaseGroupStudentsRepository)

    @Test
    fun testRemoveAllStudents(): Unit = runBlocking {

        firebaseRemoveAllGroupStudentsUseCase.removeAllStudents(GROUP_ID)

        coVerify { firebaseGroupStudentsRepository.removeAllStudents(GROUP_ID) }
    }

    companion object {
        private const val GROUP_ID = "group_id"
    }
}