package dmitriy.losev.firebase.domain.repositories.students

interface FirebaseStudentGroupsRepository {

    suspend fun getGroups(teacherId: String, studentId: String): List<String>

    suspend fun getLimitGroups(teacherId: String, studentId: String, count: Int): List<String>

    suspend fun addGroup(teacherId: String, studentId: String, groupId: String)

    suspend fun removeGroup(teacherId: String, studentId: String, groupId: String)

    suspend fun removeAllGroups(teacherId: String, studentId: String)
}