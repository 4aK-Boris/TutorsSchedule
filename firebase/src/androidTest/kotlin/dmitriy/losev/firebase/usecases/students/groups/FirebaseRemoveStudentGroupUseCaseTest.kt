package dmitriy.losev.firebase.usecases.students.groups

import dmitriy.losev.firebase.core.usecases.students.BaseStudentGroupsUseCaseTest
import dmitriy.losev.firebase.domain.usecases.students.groups.FirebaseRemoveStudentGroupUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.koin.test.inject
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class FirebaseRemoveStudentGroupUseCaseTest: BaseStudentGroupsUseCaseTest() {

    private val firebaseRemoveStudentGroupUseCase by inject<FirebaseRemoveStudentGroupUseCase>()

    override suspend fun setUp() {
        logIn()
    }

    override suspend fun tearDown() {
        deleteGroups()
        logOut()
    }

    @Test
    fun testRemoveGroup(): Unit = runBlocking {

        addGroup()

        var hasGroup = hasGroup()

        assertTrue(hasGroup)

        firebaseRemoveStudentGroupUseCase.removeGroup(STUDENT_ID, GROUP_ID)

        hasGroup = hasGroup()

        assertFalse(hasGroup)
    }
}