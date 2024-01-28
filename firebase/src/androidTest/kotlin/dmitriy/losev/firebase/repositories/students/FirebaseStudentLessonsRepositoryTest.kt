package dmitriy.losev.firebase.repositories.students

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.firebase.database.DatabaseReference
import dmitriy.losev.firebase.core.BaseRepositoryTest
import dmitriy.losev.firebase.core.LESSONS
import dmitriy.losev.firebase.core.STUDENTS
import dmitriy.losev.firebase.domain.repositories.students.FirebaseStudentLessonsRepository
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.tasks.await
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.test.inject
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

@RunWith(AndroidJUnit4::class)
class FirebaseStudentLessonsRepositoryTest : BaseRepositoryTest() {

    private val reference by inject<DatabaseReference>()

    private val lessonsReference by lazy { reference.child(STUDENTS).child(STUDENT_ID).child(LESSONS) }

    private val firebaseStudentLessonsRepository by inject<FirebaseStudentLessonsRepository>()

    override suspend fun tearDown() {
        deleteLessons()
    }

    @Test
    fun testGetAllLessons(): Unit = runBlocking {

        val count = 10

        addLessons(count = count)

        val actualResult = firebaseStudentLessonsRepository.getAllLessons(STUDENT_ID)

        assertEquals(count, actualResult.size)

        actualResult.forEachIndexed { index, studentId ->
            assertEquals("${LESSON_ID}-$index", studentId)
        }
    }

    @Test
    fun testGetLimitLessonsWithNotEnoughLessons(): Unit = runBlocking {

        val count = 10
        val countLessons = 5

        addLessons(count = countLessons)

        val actualResult = firebaseStudentLessonsRepository.getLimitLessons(STUDENT_ID, count)

        assertEquals(countLessons, actualResult.size)
    }

    @Test
    fun testGetLimitLessonsWithEnoughLessons(): Unit = runBlocking {

        val count = 10
        val countLessons = 20

        addLessons(count = countLessons)

        val actualResult = firebaseStudentLessonsRepository.getLimitLessons(STUDENT_ID, count)

        assertEquals(count, actualResult.size)
    }

    @Test
    fun testAddLesson(): Unit = runBlocking {

        firebaseStudentLessonsRepository.addLesson(STUDENT_ID, LESSON_ID)

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

        firebaseStudentLessonsRepository.removeLesson(STUDENT_ID, LESSON_ID)

        hasLesson = hasLesson()

        assertFalse(hasLesson)
    }

    @Test
    fun removeAllLessons(): Unit = runBlocking {

        val count = 10

        addLessons(count)

        var hasLessons = hasLessons()

        assertTrue(hasLessons)

        firebaseStudentLessonsRepository.removeAllLessons(STUDENT_ID)

        hasLessons = hasLessons()

        assertFalse(hasLessons)
    }


    private suspend fun addLesson() {
        addLesson(id = LESSON_ID)
    }

    private suspend fun addLesson(id: String) {
        lessonsReference.child(id).setValue(true).await()
    }

    private suspend fun addLessons(count: Int) {
        repeat(count) { index ->
            addLesson(id = "$LESSON_ID-$index")
        }
    }

    private suspend fun deleteLessons() {
        lessonsReference.removeValue().await()
    }

    private suspend fun getLesson(): String? {
        return getLesson(key = LESSON_ID)
    }

    private suspend fun getLesson(key: String): String? {
        val hasLesson = lessonsReference.child(key).get().await().getValue(Boolean::class.java)
        return if (hasLesson == true) {
            lessonsReference.child(key).key
        } else {
            null
        }
    }

    private suspend fun hasLesson(): Boolean {
        return lessonsReference.get().await().children.find { dataSnapshot ->
            dataSnapshot.key == LESSON_ID && dataSnapshot.getValue(Boolean::class.java) == true
        } != null
    }

    private suspend fun hasLessons(): Boolean {
        return lessonsReference.get().await().children.toList().isNotEmpty()
    }

    companion object {

        private const val STUDENT_ID = "d9c4983m24382c7432m748320-432"
        private const val LESSON_ID = "4324324324324234v8324324324v32"
    }
}