package dmitriy.losev.firebase.usecases.students.groups

import dmitriy.losev.firebase.core.usecases.students.BaseStudentGroupsUseCaseTest
import dmitriy.losev.firebase.domain.usecases.students.groups.FirebaseRemoveAllStudentGroupsUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.koin.test.inject
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class FirebaseRemoveAllStudentGroupsUseCaseTest: BaseStudentGroupsUseCaseTest() {

    private val firebaseRemoveAllStudentGroupsUseCase by inject<FirebaseRemoveAllStudentGroupsUseCase>()

    override suspend fun setUp() {
        logIn()
    }

    override suspend fun tearDown() {
        deleteGroups()
        logOut()
    }

    @Test
    fun removeAllGroups(): Unit = runBlocking {

        val count = 10

        addGroups(count)

        var hasGroups = hasGroups()

        assertTrue(hasGroups)

        firebaseRemoveAllStudentGroupsUseCase.removeAllGroups(STUDENT_ID)

        hasGroups = hasGroups()

        assertFalse(hasGroups)
    }
}