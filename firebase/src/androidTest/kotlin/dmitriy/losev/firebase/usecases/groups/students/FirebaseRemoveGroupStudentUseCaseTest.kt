package dmitriy.losev.firebase.usecases.groups.students

import dmitriy.losev.firebase.core.usecases.groups.BaseGroupStudentsUseCaseTest
import dmitriy.losev.firebase.domain.usecases.groups.students.FirebaseRemoveGroupStudentUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.koin.test.inject
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class FirebaseRemoveGroupStudentUseCaseTest: BaseGroupStudentsUseCaseTest() {

    private val firebaseRemoveGroupStudentUseCase by inject<FirebaseRemoveGroupStudentUseCase>()

    override suspend fun setUp() {
        logIn()
    }

    override suspend fun tearDown() {
        deleteStudents()
        logOut()
    }

    @Test
    fun testRemoveStudent(): Unit = runBlocking {

        addStudent()

        var hasStudent = hasStudent()

        assertTrue(hasStudent)

        firebaseRemoveGroupStudentUseCase.removeStudent(GROUP_ID, STUDENT_ID)

        hasStudent = hasStudent()

        assertFalse(hasStudent)
    }
}