package dmitriy.losev.firebase.usecases.groups

import dmitriy.losev.firebase.core.usecases.groups.BaseGroupsUseCaseTest
import dmitriy.losev.firebase.domain.usecases.groups.FirebaseDeleteGroupUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.koin.test.inject
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class FirebaseDeleteGroupUseCaseTest: BaseGroupsUseCaseTest() {

    private val firebaseDeleteGroupUseCase by inject<FirebaseDeleteGroupUseCase>()

    override suspend fun setUp() {
        logIn()
        addGroup()
    }

    override suspend fun tearDown() {
        logOut()
    }

    @Test
    fun testDeleteGroup(): Unit = runBlocking {

        var hasGroup =  hasGroup()

        assertTrue(hasGroup)

        firebaseDeleteGroupUseCase.deleteGroup(GROUP_ID)

        hasGroup = hasGroup()

        assertFalse(hasGroup)
    }
}