package dmitriy.losev.firebase.domain.repositories.groups

interface FirebaseGroupLessonsRepository {

    suspend fun getAllLessons(groupId: String): List<String>

    suspend fun addLesson(groupId: String, lessonId: String)

    suspend fun removeLesson(groupId: String, lessonId: String)

    suspend fun removeAllLessons(groupId: String)
}