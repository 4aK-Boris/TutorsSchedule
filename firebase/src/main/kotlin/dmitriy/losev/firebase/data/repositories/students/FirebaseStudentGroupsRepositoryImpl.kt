package dmitriy.losev.firebase.data.repositories.students

import com.google.firebase.database.DatabaseReference
import dmitriy.losev.firebase.core.GROUPS
import dmitriy.losev.firebase.core.STUDENTS
import dmitriy.losev.firebase.domain.repositories.students.FirebaseStudentGroupsRepository
import kotlinx.coroutines.tasks.await

class FirebaseStudentGroupsRepositoryImpl(private val reference: DatabaseReference) : FirebaseStudentGroupsRepository {

    override suspend fun getGroups(teacherId: String, studentId: String): List<String> {
        return getStudentGroupsReference(teacherId, studentId).get().await().children
            .filter { dataSnapshot -> dataSnapshot.getValue(Boolean::class.java) == true }
            .mapNotNull { dataSnapshot -> dataSnapshot.key }

    }

    override suspend fun getLimitGroups(teacherId: String, studentId: String, count: Int): List<String> {
        return getStudentGroupsReference(teacherId, studentId).limitToFirst(count).get().await().children
            .filter { dataSnapshot -> dataSnapshot.getValue(Boolean::class.java) == true }
            .mapNotNull { dataSnapshot -> dataSnapshot.key }
    }

    override suspend fun addGroup(teacherId: String, studentId: String, groupId: String) {
        getStudentGroupsReference(teacherId, studentId).child(groupId).setValue(true)
    }

    override suspend fun removeGroup(teacherId: String, studentId: String, groupId: String) {
        getStudentGroupsReference(teacherId, studentId).child(groupId).removeValue()
    }

    override suspend fun removeAllGroups(teacherId: String, studentId: String) {
        getStudentGroupsReference(teacherId, studentId).removeValue()
    }

    private fun getStudentGroupsReference(teacherId: String, studentId: String): DatabaseReference {
        return reference.child(teacherId).child(STUDENTS).child(studentId).child(GROUPS)
    }
}