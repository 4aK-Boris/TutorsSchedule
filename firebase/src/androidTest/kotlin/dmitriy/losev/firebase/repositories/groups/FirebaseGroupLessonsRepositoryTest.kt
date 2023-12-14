package dmitriy.losev.firebase.repositories.groups

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.firebase.database.DatabaseReference
import dmitriy.losev.firebase.core.BaseRepositoryTest
import dmitriy.losev.firebase.core.GROUPS
import dmitriy.losev.firebase.core.LESSONS
import dmitriy.losev.firebase.domain.repositories.groups.FirebaseGroupLessonsRepository
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.tasks.await
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.test.inject
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

@RunWith(AndroidJUnit4::class)
class FirebaseGroupLessonsRepositoryTest : BaseRepositoryTest() {

    private val reference by inject<DatabaseReference>()

    private val groupLessonsReference by lazy { reference.child(GROUPS).child(LESSONS) }

    private val firebaseGroupLessonsRepository by inject<FirebaseGroupLessonsRepository>()

    override suspend fun tearDown() {
        deleteLessons()
    }

    @Test
    fun testGetAllLessons(): Unit = runBlocking {

        val count = 10

        addLessons(count = count)

        val actualResult = firebaseGroupLessonsRepository.getAllLessons(GROUP_ID)

        assertEquals(count, actualResult.size)

        actualResult.forEachIndexed { index, lessonId ->
            assertEquals("${LESSON_ID}-$index", lessonId)
        }
    }

    @Test
    fun testAddLesson(): Unit = runBlocking {

        firebaseGroupLessonsRepository.addLesson(GROUP_ID, LESSON_ID)

        val hasLesson = hasLesson()

        assertTrue(hasLesson)

        val actualResult = getLesson()

        assertEquals(LESSON_ID, actualResult)
    }

    @Test
    fun testRemoveLesson(): Unit = runBlocking {

        addLesson()

        var hasLesson = hasLesson()

        assertTrue(hasLesson)

        firebaseGroupLessonsRepository.removeLesson(GROUP_ID, LESSON_ID)

        hasLesson = hasLesson()

        assertFalse(hasLesson)
    }

    @Test
    fun removeAllLessons(): Unit = runBlocking {

        val count = 10

        addLessons(count)

        var hasLessons = hasLessons()

        assertTrue(hasLessons)

        firebaseGroupLessonsRepository.removeAllLessons(GROUP_ID)

        hasLessons = hasLessons()

        assertFalse(hasLessons)
    }


    private suspend fun addLesson() {
        addLesson(id = LESSON_ID)
    }

    private suspend fun addLesson(id: String) {
        groupLessonsReference.child(GROUP_ID).child(id).setValue(true).await()
    }

    private suspend fun addLessons(count: Int) {
        repeat(count) { index ->
            addLesson(id = "$LESSON_ID-$index")
        }
    }

    private suspend fun deleteLessons() {
        groupLessonsReference.child(GROUP_ID).removeValue().await()
    }

    private suspend fun getLesson(): String? {
        return getLesson(key = LESSON_ID)
    }

    private suspend fun getLesson(key: String): String? {
        val hasLessonInGroup = groupLessonsReference.child(GROUP_ID).child(key).get().await().getValue(Boolean::class.java)
        return if (hasLessonInGroup == true) {
            groupLessonsReference.child(GROUP_ID).child(key).key
        } else {
            null
        }
    }

    private suspend fun hasLesson(): Boolean {
        return groupLessonsReference.child(GROUP_ID).get().await().children.find { dataSnapshot ->
            dataSnapshot.key == LESSON_ID && dataSnapshot.getValue(Boolean::class.java) == true
        } != null
    }

    private suspend fun hasLessons(): Boolean {
        return groupLessonsReference.child(GROUP_ID).get().await().children.toList().isNotEmpty()
    }

    companion object {

        private const val GROUP_ID = "4324324324324234v8324324324v32"
        private const val LESSON_ID = "d9c4983m24382c7432m748320-432"
    }
}