package dmitriy.losev.firebase.usecases.groups

import dmitriy.losev.firebase.core.usecases.groups.BaseGroupsUseCaseTest
import dmitriy.losev.firebase.domain.usecases.groups.FirebaseGetGroupsUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.koin.test.inject
import kotlin.test.assertEquals

class FirebaseGetGroupsUseCaseTest : BaseGroupsUseCaseTest() {

    private val firebaseGetSimpleGroupsUseCase by inject<FirebaseGetGroupsUseCase>()

    override suspend fun setUp() {
        logIn()
        addGroup(ID_1)
        addGroup(ID_2)
        addGroup(ID_3)
    }

    override suspend fun tearDown() {
        deleteGroups()
        logOut()
    }

    @Test
    fun testGetGroups(): Unit = runBlocking {

        val actualResult = firebaseGetSimpleGroupsUseCase.getGroups()

        assertEquals(SIZE, actualResult.size)

        actualResult.forEachIndexed { index, otherGroup ->
            assertEquals("id_${index + 1}", otherGroup.id)
            assertEquals(NAME, otherGroup.name)
        }
    }

    companion object {
        private const val SIZE = 3

        private const val ID_1 = "id_1"
        private const val ID_2 = "id_2"
        private const val ID_3 = "id_3"
    }
}