package dmitriy.losev.firebase.usecases.groups

import dmitriy.losev.firebase.core.usecases.groups.BaseGroupsUseCaseTest
import dmitriy.losev.firebase.domain.usecases.groups.FirebaseAddGroupUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.koin.test.inject
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class FirebaseAddGroupUseCaseTest: BaseGroupsUseCaseTest() {

    private val firebaseAddGroupUseCase by inject<FirebaseAddGroupUseCase>()

    override suspend fun setUp() {
        logIn()
    }

    override suspend fun tearDown() {
        deleteGroups()
        logOut()
    }

    @Test
    fun testAddGroup(): Unit = runBlocking {

        val key = firebaseAddGroupUseCase.addGroup(group)

        val hasGroup = hasGroup()

        assertTrue(hasGroup)

        val actualResult = getGroup(key)

        assertEquals(group.copy(id = key), actualResult)
    }
}