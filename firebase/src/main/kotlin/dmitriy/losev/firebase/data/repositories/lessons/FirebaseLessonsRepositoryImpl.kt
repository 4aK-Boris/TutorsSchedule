package dmitriy.losev.firebase.data.repositories.lessons

import com.google.firebase.database.DatabaseReference
import dmitriy.losev.firebase.core.LESSONS
import dmitriy.losev.firebase.data.dto.LessonDTO
import dmitriy.losev.firebase.data.mappers.LessonMapper
import dmitriy.losev.firebase.domain.models.Lesson
import dmitriy.losev.firebase.domain.repositories.lessons.FirebaseLessonsRepository
import kotlinx.coroutines.tasks.await

class FirebaseLessonsRepositoryImpl(
    reference: DatabaseReference,
    private val lessonMapper: LessonMapper,
): FirebaseLessonsRepository {

    private val lessons by lazy { reference.child(LESSONS) }

    override suspend fun addLesson(teacherId: String, lesson: Lesson): String? {
        val lessonDTO = lessonMapper.map(value = lesson)
        lessons.child(teacherId).push().apply {
            setValue(lessonDTO.copy(id = key)).await()
            return key
        }
    }

    override suspend fun getLesson(teacherId: String, lessonId: String): Lesson? {
        return lessons.child(teacherId).child(lessonId).get().await().getValue(LessonDTO::class.java)?.let { lessonDTO ->
            lessonMapper.map(value = lessonDTO)
        }
    }

    override suspend fun updateLesson(teacherId: String, lessonId: String, lesson: Lesson) {
        lessons.child(teacherId).updateChildren(mapOf(lessonId to lessonMapper.map(value = lesson))).await()
    }

    override suspend fun deleteLesson(teacherId: String, lessonId: String) {
        lessons.child(teacherId).child(lessonId).removeValue().await()
    }

    override suspend fun getLessons(teacherId: String): List<Lesson> {
        return lessons.child(teacherId).get().await().children.mapNotNull { dataSnapshot ->
            dataSnapshot.getValue(LessonDTO::class.java)?.let { lessonDTO -> lessonMapper.map(value = lessonDTO) }
        }
    }
}