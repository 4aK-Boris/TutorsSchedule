package dmitriy.losev.firebase.usecases.groups.students

import dmitriy.losev.firebase.domain.repositories.groups.FirebaseGroupStudentsRepository
import dmitriy.losev.firebase.domain.usecases.groups.students.FirebaseRemoveGroupStudentUseCase
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test

class FirebaseRemoveGroupStudentUseCaseTest {

    private val firebaseGroupStudentsRepository = mockk<FirebaseGroupStudentsRepository>(relaxed = true)

    private val firebaseRemoveGroupStudentUseCase = FirebaseRemoveGroupStudentUseCase(firebaseGroupStudentsRepository)

    @Test
    fun testRemoveStudent(): Unit = runBlocking {

        firebaseRemoveGroupStudentUseCase.removeStudent(GROUP_ID, STUDENT_ID)

        coVerify { firebaseGroupStudentsRepository.removeStudent(GROUP_ID, STUDENT_ID) }
    }

    companion object {
        private const val GROUP_ID = "group_id"
        private const val STUDENT_ID = "student_id"
    }
}