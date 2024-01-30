package dmitriy.losev.firebase.domain.repositories.groups

interface FirebaseGroupLessonsRepository {

    suspend fun getAllLessons(teacherId: String, groupId: String): List<String>

    suspend fun addLesson(teacherId: String, groupId: String, lessonId: String)

    suspend fun removeLesson(teacherId: String, groupId: String, lessonId: String)

    suspend fun removeAllLessons(teacherId: String, groupId: String)
}