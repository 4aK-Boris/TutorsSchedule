package dmitriy.losev.firebase.core.usecases.lessons

import com.google.firebase.database.DatabaseReference
import dmitriy.losev.firebase.core.BaseUseCaseTest
import dmitriy.losev.firebase.core.LESSONS
import dmitriy.losev.firebase.core.exception.UserNotAuthorizationException
import dmitriy.losev.firebase.data.dto.LessonDTO
import dmitriy.losev.firebase.data.mappers.LessonMapper
import dmitriy.losev.firebase.domain.models.Lesson
import dmitriy.losev.core.models.types.LessonType
import kotlinx.coroutines.tasks.await
import org.koin.test.inject

abstract class BaseLessonsUseCaseTest : BaseUseCaseTest() {

    private val reference by inject<DatabaseReference>()

    private val lessonMapper by inject<LessonMapper>()

    private val lessonsReference by lazy { reference.child(LESSONS) }
    private val userUID by lazy { auth.currentUser?.uid ?: throw UserNotAuthorizationException() }

    protected suspend fun addLesson() {
        lessonsReference.child(userUID).child(LESSON_ID).setValue(lessonMapper.map(value = lesson)).await()
    }

    protected suspend fun addLesson(id: String) {
        lessonsReference.child(userUID).child(id).setValue(lessonMapper.map(value = lesson.copy(id = id))).await()
    }

    protected suspend fun deleteLessons() {
        lessonsReference.child(userUID).get().await().children.forEach { lesson ->
            lesson.ref.removeValue().await()
        }
    }

    protected suspend fun getLesson(): Lesson? {
        return lessonsReference.child(userUID).child(LESSON_ID).get().await().getValue(LessonDTO::class.java)?.let { lessonDTO ->
            lessonMapper.map(value = lessonDTO)
        }
    }

    protected suspend fun getLesson(key: String): Lesson? {
        return lessonsReference.child(userUID).child(key).get().await().getValue(LessonDTO::class.java)?.let { lessonDTO ->
            lessonMapper.map(value = lessonDTO)
        }
    }

    protected suspend fun hasLesson(): Boolean {
        return lessonsReference.child(userUID).get().await().children.toList().isNotEmpty()
    }

    companion object {

        const val LESSON_ID = "4324324324324234v8324324324v32"
        private const val STUDENT_OR_GROUP_ID = "v785n-39472m58 4m32958 430-958 43"
        private const val SUBJECT_ID = " m5pou4opiu6p4v3 iu65p84ou68ip54u6"
        private const val PRICE = 1000
        private const val DURATION = 90

        private val LESSON_TYPE = LessonType.GROUP

        val lesson = Lesson(
            id = LESSON_ID,
            type = LESSON_TYPE,
            studentOrGroupId = STUDENT_OR_GROUP_ID,
            subjectId = SUBJECT_ID,
            price = PRICE,
            duration = DURATION
        )
    }
}