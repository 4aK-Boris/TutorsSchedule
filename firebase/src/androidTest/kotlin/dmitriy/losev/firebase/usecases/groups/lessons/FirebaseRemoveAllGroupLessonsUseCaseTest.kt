package dmitriy.losev.firebase.usecases.groups.lessons

import dmitriy.losev.firebase.core.usecases.groups.BaseGroupLessonsUseCaseTest
import dmitriy.losev.firebase.domain.usecases.groups.lessons.FirebaseRemoveAllGroupLessonsUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.koin.test.inject
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class FirebaseRemoveAllGroupLessonsUseCaseTest: BaseGroupLessonsUseCaseTest() {

    private val firebaseRemoveAllGroupLessonsUseCase by inject<FirebaseRemoveAllGroupLessonsUseCase>()

    override suspend fun setUp() {
        logIn()
    }

    override suspend fun tearDown() {
        deleteLessons()
        logOut()
    }

    @Test
    fun removeAllLessons(): Unit = runBlocking {

        val count = 10

        addLessons(count)

        var hasLessons = hasLessons()

        assertTrue(hasLessons)

        firebaseRemoveAllGroupLessonsUseCase.removeAllLessons(GROUP_ID)

        hasLessons = hasLessons()

        assertFalse(hasLessons)
    }
}