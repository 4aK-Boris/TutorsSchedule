package dmitriy.losev.firebase.repositories.lessons

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.firebase.database.DatabaseReference
import dmitriy.losev.firebase.core.BaseRepositoryTest
import dmitriy.losev.firebase.core.LESSONS
import dmitriy.losev.firebase.data.dto.LessonDTO
import dmitriy.losev.firebase.data.mappers.LessonMapper
import dmitriy.losev.firebase.domain.models.Lesson
import dmitriy.losev.core.models.types.LessonType
import dmitriy.losev.firebase.domain.repositories.lessons.FirebaseLessonsRepository
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.tasks.await
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.test.inject
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

@RunWith(AndroidJUnit4::class)
class FirebaseLessonsRepositoryTest : BaseRepositoryTest() {

    private val reference by inject<DatabaseReference>()
    private val lessonMapper by inject<LessonMapper>()

    private val lessonReference by lazy { reference.child(LESSONS) }

    private val firebaseLessonsRepository by inject<FirebaseLessonsRepository>()

    override suspend fun tearDown() {
        deleteLessons()
    }

    @Test
    fun testAddLesson(): Unit = runBlocking {

        val key = firebaseLessonsRepository.addLesson(userUID, lesson)!!

        val hasLesson = hasLesson()

        assertTrue(hasLesson)

        val actualResult = getLesson(key)

        assertEquals(lesson.copy(id = key), actualResult)
    }

    @Test
    fun testUpdateLesson(): Unit = runBlocking {

        addLesson()

        val hasLesson = hasLesson()

        assertTrue(hasLesson)

        val newLesson = lesson.copy(type = NEW_LESSON_TYPE, price = NEW_PRICE, duration = NEW_DURATION)

        firebaseLessonsRepository.updateLesson(userUID, LESSON_ID, newLesson)

        val actualResult = getLesson()

        assertEquals(newLesson, actualResult)
    }

    @Test
    fun testDeleteLesson(): Unit = runBlocking {

        addLesson()

        var hasLesson = hasLesson()

        assertTrue(hasLesson)

        firebaseLessonsRepository.deleteLesson(userUID, LESSON_ID)

        hasLesson = hasLesson()

        assertFalse(hasLesson)
    }

    @Test
    fun testGetLesson(): Unit = runBlocking {

        addLesson()

        val hasLesson = hasLesson()

        assertTrue(hasLesson)

        val actualResult = firebaseLessonsRepository.getLesson(userUID, LESSON_ID)

        assertEquals(lesson, actualResult)
    }

    @Test
    fun testGetLessons(): Unit = runBlocking {

        val size = 3

        repeat(size) { index ->
            addLesson(id = "${LESSON_ID}$index")
        }

        val actualResult = firebaseLessonsRepository.getLessons(userUID)

        assertEquals(size, actualResult.size)

        actualResult.forEachIndexed { index, manyLesson ->
            assertEquals(lesson.copy(id = "${LESSON_ID}$index"), manyLesson)
        }
    }


    private suspend fun addLesson() {
        addLesson(id = LESSON_ID)
    }

    private suspend fun addLesson(id: String) {
        lessonReference.child(userUID).child(id).setValue(lessonMapper.map(value = lesson.copy(id = id))).await()
    }

    private suspend fun deleteLessons() {
        lessonReference.child(userUID).get().await().children.forEach { dataSnapShot ->
            dataSnapShot.ref.removeValue().await()
        }
    }

    private suspend fun getLesson(): Lesson? {
        return getLesson(key = LESSON_ID)
    }

    private suspend fun getLesson(key: String): Lesson? {
        return lessonReference.child(userUID).child(key).get().await().getValue(LessonDTO::class.java)?.let { lessonDTO ->
            lessonMapper.map(value = lessonDTO)
        }
    }

    private suspend fun hasLesson(): Boolean {
        return lessonReference.child(userUID).get().await().children.toList().isNotEmpty()
    }

    companion object {

        private const val LESSON_ID = "4324324324324234v8324324324v32"
        private const val STUDENT_OR_GROUP_ID = "v785n-39472m58 4m32958 430-958 43"
        private const val SUBJECT_ID = " m5pou4opiu6p4v3 iu65p84ou68ip54u6"
        private const val PRICE = 1000
        private const val DURATION = 90

        private const val NEW_PRICE = 2000
        private const val NEW_DURATION = 60

        private val LESSON_TYPE = LessonType.GROUP
        private val NEW_LESSON_TYPE = LessonType.STUDENT

        private val lesson = Lesson(
            id = LESSON_ID,
            type = LESSON_TYPE,
            studentOrGroupId = STUDENT_OR_GROUP_ID,
            subjectId = SUBJECT_ID,
            price = PRICE,
            duration = DURATION
        )
    }
}