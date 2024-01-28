package dmitriy.losev.firebase.usecases.students.groups

import dmitriy.losev.firebase.core.usecases.students.BaseStudentGroupsUseCaseTest
import dmitriy.losev.firebase.domain.usecases.students.groups.FirebaseGetLimitStudentGroupsUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.koin.test.inject
import kotlin.test.assertEquals

class FirebaseGetLimitStudentGroupsUseCaseTest: BaseStudentGroupsUseCaseTest() {

    private val firebaseGetLimitStudentGroupsUseCase by inject<FirebaseGetLimitStudentGroupsUseCase>()

    override suspend fun setUp() {
        logIn()
    }

    override suspend fun tearDown() {
        deleteGroups()
        logOut()
    }

    @Test
    fun testGetLimitGroupsWithEnoughGroups(): Unit = runBlocking {

        val count = 10
        val groupsCount = 20

        addGroups(count = groupsCount)

        val actualResult = firebaseGetLimitStudentGroupsUseCase.getLimitGroups(STUDENT_ID, count)

        assertEquals(count, actualResult.size)
    }

    @Test
    fun testGetLimitGroupsWithNotEnoughGroups(): Unit = runBlocking {

        val count = 10
        val groupsCount = 5

        addGroups(count = groupsCount)

        val actualResult = firebaseGetLimitStudentGroupsUseCase.getLimitGroups(STUDENT_ID, count)

        assertEquals(groupsCount, actualResult.size)
    }
}