package dmitriy.losev.firebase.usecases.subjects

import dmitriy.losev.firebase.core.usecases.subjects.BaseSubjectsUseCaseTest
import dmitriy.losev.firebase.domain.usecases.subjects.FirebaseDeleteSubjectUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.koin.test.inject
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class FirebaseDeleteSubjectUseCaseTest: BaseSubjectsUseCaseTest() {

    private val firebaseDeleteSubjectUseCase by inject<FirebaseDeleteSubjectUseCase>()

    override suspend fun setUp() {
        logIn()
        addSubject()
    }

    override suspend fun tearDown() {
        logOut()
    }

    @Test
    fun testDeleteSubject(): Unit = runBlocking {

        var hasSubject =  hasSubject()

        assertTrue(hasSubject)

        firebaseDeleteSubjectUseCase.deleteSubject(SUBJECT_ID)

        hasSubject = hasSubject()

        assertFalse(hasSubject)
    }
}