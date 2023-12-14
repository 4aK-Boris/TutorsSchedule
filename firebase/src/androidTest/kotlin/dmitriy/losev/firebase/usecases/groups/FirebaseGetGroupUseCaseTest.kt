package dmitriy.losev.firebase.usecases.groups

import dmitriy.losev.firebase.core.usecases.groups.BaseGroupsUseCaseTest
import dmitriy.losev.firebase.domain.usecases.groups.FirebaseGetGroupUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.koin.test.inject
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class FirebaseGetGroupUseCaseTest: BaseGroupsUseCaseTest() {

    private val firebaseGetGroupUseCase by inject<FirebaseGetGroupUseCase>()

    override suspend fun setUp() {
        logIn()
        addGroup()
    }

    override suspend fun tearDown() {
        deleteGroups()
        logOut()
    }

    @Test
    fun testGetGroup(): Unit = runBlocking {

        val hasGroup =  hasGroup()

        assertTrue(hasGroup)

        val actualResult = firebaseGetGroupUseCase.getGroup(GROUP_ID)

        assertEquals(group, actualResult)
    }
}