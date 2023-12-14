package dmitriy.losev.firebase.domain.repositories.groups

interface FirebaseGroupStudentsRepository {

    suspend fun getAllStudents(groupId: String): List<String>

    suspend fun addStudent(groupId: String, studentId: String)

    suspend fun removeStudent(groupId: String, studentId: String)

    suspend fun removeAllStudents(groupId: String)
}