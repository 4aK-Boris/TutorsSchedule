package dmitriy.losev.firebase.usecases.groups.students

import dmitriy.losev.firebase.core.usecases.groups.BaseGroupStudentsUseCaseTest
import dmitriy.losev.firebase.domain.usecases.groups.students.FirebaseRemoveGroupStudentsUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.koin.test.inject
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class FirebaseRemoveAllGroupStudentsUseCaseTest: BaseGroupStudentsUseCaseTest() {

    private val firebaseRemoveGroupStudentsUseCase by inject<FirebaseRemoveGroupStudentsUseCase>()

    override suspend fun setUp() {
        logIn()
    }

    override suspend fun tearDown() {
        deleteStudents()
        logOut()
    }

    @Test
    fun removeAllStudents(): Unit = runBlocking {

        val count = 10

        addStudents(count)

        var hasStudents = hasStudents()

        assertTrue(hasStudents)

        firebaseRemoveGroupStudentsUseCase.removeGroupStudents(GROUP_ID)

        hasStudents = hasStudents()

        assertFalse(hasStudents)
    }
}