package dmitriy.losev.firebase.domain.repositories.students

interface FirebaseStudentGroupsRepository {

    suspend fun getAllGroups(studentId: String): List<String>

    suspend fun addGroup(studentId: String, groupId: String)

    suspend fun removeGroup(studentId: String, groupId: String)

    suspend fun removeAllGroups(studentId: String)
}