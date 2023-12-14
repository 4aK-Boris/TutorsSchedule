package dmitriy.losev.firebase.usecases.students.groups

import dmitriy.losev.firebase.core.usecases.students.BaseStudentGroupsUseCaseTest
import dmitriy.losev.firebase.domain.usecases.students.groups.FirebaseAddStudentGroupUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.koin.test.inject
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class FirebaseAddStudentGroupUseCaseTest: BaseStudentGroupsUseCaseTest() {

    private val firebaseAddStudentGroupUseCase by inject<FirebaseAddStudentGroupUseCase>()

    override suspend fun setUp() {
        logIn()
    }

    override suspend fun tearDown() {
        deleteGroups()
        logOut()
    }

    @Test
    fun testAddGroup(): Unit = runBlocking {

        firebaseAddStudentGroupUseCase.addGroup(STUDENT_ID, GROUP_ID)

        val hasGroup = hasGroup()

        assertTrue(hasGroup)

        val actualResult = getGroup()

        assertEquals(GROUP_ID, actualResult)
    }
}