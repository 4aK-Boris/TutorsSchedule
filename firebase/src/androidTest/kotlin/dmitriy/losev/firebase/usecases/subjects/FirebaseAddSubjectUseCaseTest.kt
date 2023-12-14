package dmitriy.losev.firebase.usecases.subjects

import dmitriy.losev.firebase.core.usecases.subjects.BaseSubjectsUseCaseTest
import dmitriy.losev.firebase.domain.usecases.subjects.FirebaseAddSubjectUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.koin.test.inject
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class FirebaseAddSubjectUseCaseTest: BaseSubjectsUseCaseTest() {

    private val firebaseAddSubjectUseCase by inject<FirebaseAddSubjectUseCase>()

    override suspend fun setUp() {
        logIn()
    }

    override suspend fun tearDown() {
        deleteSubjects()
        logOut()
    }

    @Test
    fun testAddSubject(): Unit = runBlocking {

        val key = firebaseAddSubjectUseCase.addSubject(subject)

        val hasSubject = hasSubject()

        assertTrue(hasSubject)

        val actualResult = getSubject(key)

        assertEquals(subject.copy(id = key), actualResult)
    }
}