package dmitriy.losev.firebase.domain.repositories.groups

interface FirebaseGroupStudentsRepository {

    suspend fun getStudents(teacherId: String, groupId: String): List<String>

    suspend fun addStudent(teacherId: String, groupId: String, studentId: String)

    suspend fun removeStudent(teacherId: String, groupId: String, studentId: String)

    suspend fun removeStudents(teacherId: String, groupId: String)
}