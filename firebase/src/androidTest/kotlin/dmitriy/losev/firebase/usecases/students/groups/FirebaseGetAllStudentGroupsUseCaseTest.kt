package dmitriy.losev.firebase.usecases.students.groups

import dmitriy.losev.firebase.core.usecases.students.BaseStudentGroupsUseCaseTest
import dmitriy.losev.firebase.domain.usecases.students.groups.FirebaseGetAllStudentGroupsUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.koin.test.inject
import kotlin.test.assertEquals

class FirebaseGetAllStudentGroupsUseCaseTest: BaseStudentGroupsUseCaseTest() {

    private val firebaseGetAllStudentGroupsUseCase by inject<FirebaseGetAllStudentGroupsUseCase>()

    override suspend fun setUp() {
        logIn()
    }

    override suspend fun tearDown() {
        deleteGroups()
        logOut()
    }

    @Test
    fun testGetAllGroups(): Unit = runBlocking {

        val count = 10

        addGroups(count = count)

        val actualResult = firebaseGetAllStudentGroupsUseCase.getAllGroups(STUDENT_ID)

        assertEquals(count, actualResult.size)

        actualResult.forEachIndexed { index, groupId ->
            assertEquals("${GROUP_ID}-$index", groupId)
        }
    }
}