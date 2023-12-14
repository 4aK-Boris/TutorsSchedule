package dmitriy.losev.firebase.domain.repositories.lessons

import dmitriy.losev.firebase.domain.models.Lesson

interface FirebaseLessonsRepository {

    suspend fun addLesson(teacherId: String, lesson: Lesson): String?

    suspend fun getLesson(teacherId: String, lessonId: String): Lesson?

    suspend fun updateLesson(teacherId: String, lessonId: String, lesson: Lesson)

    suspend fun deleteLesson(teacherId: String, lessonId: String)

    suspend fun getLessons(teacherId: String): List<Lesson>
}