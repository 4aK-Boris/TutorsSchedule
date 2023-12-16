package dmitriy.losev.firebase.domain.repositories.students

interface FirebaseStudentLessonsRepository {

    suspend fun getAllLessons(studentId: String): List<String>

    suspend fun getLimitLessons(studentId: String, count: Int): List<String>

    suspend fun addLesson(studentId: String, lessonId: String)

    suspend fun removeLesson(studentId: String, lessonId: String)

    suspend fun removeAllLessons(studentId: String)
}