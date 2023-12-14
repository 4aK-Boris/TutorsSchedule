package dmitriy.losev.firebase.usecases.subjects

import dmitriy.losev.firebase.core.usecases.subjects.BaseSubjectsUseCaseTest
import dmitriy.losev.firebase.domain.usecases.subjects.FirebaseGetSubjectsUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.koin.test.inject
import kotlin.test.assertEquals

class FirebaseGetSubjectsUseCaseTest : BaseSubjectsUseCaseTest() {

    private val firebaseGetSimpleSubjectsUseCase by inject<FirebaseGetSubjectsUseCase>()

    override suspend fun setUp() {
        logIn()
        addSubject(ID_1)
        addSubject(ID_2)
        addSubject(ID_3)
    }

    override suspend fun tearDown() {
        deleteSubjects()
        logOut()
    }

    @Test
    fun testGetSubjects(): Unit = runBlocking {

        val actualResult = firebaseGetSimpleSubjectsUseCase.getSubjects()

        assertEquals(SIZE, actualResult.size)

        actualResult.forEachIndexed { index, otherSubject ->
            assertEquals("id_${index + 1}", otherSubject.id)
            assertEquals(NAME, otherSubject.name)
        }
    }

    companion object {
        private const val SIZE = 3

        private const val ID_1 = "id_1"
        private const val ID_2 = "id_2"
        private const val ID_3 = "id_3"
    }
}