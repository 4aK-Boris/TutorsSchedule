package dmitriy.losev.firebase.usecases.groups

import dmitriy.losev.firebase.core.usecases.groups.BaseGroupsUseCaseTest
import dmitriy.losev.firebase.domain.usecases.groups.FirebaseUpdateGroupUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.koin.test.inject
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class FirebaseUpdateGroupUseCaseTest: BaseGroupsUseCaseTest() {

    private val firebaseUpdateGroupUseCase by inject<FirebaseUpdateGroupUseCase>()

    override suspend fun setUp() {
        logIn()
        addGroup()
    }

    override suspend fun tearDown() {
        deleteGroups()
        logOut()
    }

    @Test
    fun testUpdateGroup(): Unit = runBlocking {

        val hasGroup =  hasGroup()

        assertTrue(hasGroup)

        val newGroup = group.copy(name = NAME)

        firebaseUpdateGroupUseCase.updateGroup(GROUP_ID, newGroup)

        val actualResult = getGroup()

        assertEquals(newGroup, actualResult)
    }

    companion object {
        private const val NAME = "Группа для Иры"
    }
}