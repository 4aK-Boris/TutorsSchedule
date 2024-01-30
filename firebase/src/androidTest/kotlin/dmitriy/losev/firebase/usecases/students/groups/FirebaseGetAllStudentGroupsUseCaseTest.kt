package dmitriy.losev.firebase.usecases.students.groups

import dmitriy.losev.firebase.core.usecases.students.BaseStudentGroupsUseCaseTest
import dmitriy.losev.firebase.domain.usecases.students.groups.FirebaseGetStudentGroupIdsUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.koin.test.inject
import kotlin.test.assertEquals

class FirebaseGetAllStudentGroupsUseCaseTest: BaseStudentGroupsUseCaseTest() {

    private val firebaseGetStudentGroupIdsUseCase by inject<FirebaseGetStudentGroupIdsUseCase>()

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

        val actualResult = firebaseGetStudentGroupIdsUseCase.getGroupIds(STUDENT_ID)

        assertEquals(count, actualResult.size)

        actualResult.forEachIndexed { index, groupId ->
            assertEquals("${GROUP_ID}-$index", groupId)
        }
    }
}