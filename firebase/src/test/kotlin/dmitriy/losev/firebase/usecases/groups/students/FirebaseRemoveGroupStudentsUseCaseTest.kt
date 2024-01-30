package dmitriy.losev.firebase.usecases.groups.students

import dmitriy.losev.firebase.domain.repositories.groups.FirebaseGroupStudentsRepository
import dmitriy.losev.firebase.domain.usecases.groups.students.FirebaseRemoveGroupStudentsUseCase
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test

class FirebaseRemoveGroupStudentsUseCaseTest {

    private val firebaseGroupStudentsRepository = mockk<FirebaseGroupStudentsRepository>(relaxed = true)

    private val firebaseRemoveGroupStudentsUseCase = FirebaseRemoveGroupStudentsUseCase(firebaseGroupStudentsRepository)

    @Test
    fun testRemoveAllStudents(): Unit = runBlocking {

        firebaseRemoveGroupStudentsUseCase.removeGroupStudents(GROUP_ID)

        coVerify { firebaseGroupStudentsRepository.removeStudents(GROUP_ID) }
    }

    companion object {
        private const val GROUP_ID = "group_id"
    }
}