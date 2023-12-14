package dmitriy.losev.firebase.usecases.subjects

import dmitriy.losev.firebase.core.usecases.subjects.BaseSubjectsUseCaseTest
import dmitriy.losev.firebase.domain.usecases.subjects.FirebaseGetSubjectUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.koin.test.inject
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class FirebaseGetSubjectUseCaseTest: BaseSubjectsUseCaseTest() {

    private val firebaseGetSubjectUseCase by inject<FirebaseGetSubjectUseCase>()

    override suspend fun setUp() {
        logIn()
        addSubject()
    }

    override suspend fun tearDown() {
        deleteSubjects()
        logOut()
    }

    @Test
    fun testGetSubject(): Unit = runBlocking {

        val hasSubject =  hasSubject()

        assertTrue(hasSubject)

        val actualResult = firebaseGetSubjectUseCase.getSubject(SUBJECT_ID)

        assertEquals(subject, actualResult)
    }
}