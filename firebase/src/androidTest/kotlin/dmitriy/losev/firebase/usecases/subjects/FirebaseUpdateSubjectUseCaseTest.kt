package dmitriy.losev.firebase.usecases.subjects

import dmitriy.losev.firebase.core.usecases.subjects.BaseSubjectsUseCaseTest
import dmitriy.losev.firebase.domain.usecases.subjects.FirebaseUpdateSubjectUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.koin.test.inject
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class FirebaseUpdateSubjectUseCaseTest: BaseSubjectsUseCaseTest() {

    private val firebaseUpdateSubjectUseCase by inject<FirebaseUpdateSubjectUseCase>()

    override suspend fun setUp() {
        logIn()
        addSubject()
    }

    override suspend fun tearDown() {
        deleteSubjects()
        logOut()
    }

    @Test
    fun testUpdateSubject(): Unit = runBlocking {

        val hasSubject =  hasSubject()

        assertTrue(hasSubject)

        val newSubject = subject.copy(name = NAME, price = PRICE)

        firebaseUpdateSubjectUseCase.updateSubject(SUBJECT_ID, newSubject)

        val actualResult = getSubject()

        assertEquals(newSubject, actualResult)
    }

    companion object {
        private const val NAME = "Группа для Иры"
        private const val PRICE = 5000
    }
}