package dmitriy.losev.firebase.data.repositories.students

import com.google.firebase.database.DatabaseReference
import dmitriy.losev.firebase.core.GROUPS
import dmitriy.losev.firebase.core.STUDENTS
import dmitriy.losev.firebase.domain.repositories.students.FirebaseStudentGroupsRepository
import kotlinx.coroutines.tasks.await

class FirebaseStudentGroupsRepositoryImpl(reference: DatabaseReference) : FirebaseStudentGroupsRepository {

    private val studentsGroups by lazy { reference.child(STUDENTS).child(GROUPS) }
    override suspend fun getAllGroups(studentId: String): List<String> {
        return studentsGroups.child(studentId).get().await().children
            .filter { dataSnapshot -> dataSnapshot.getValue(Boolean::class.java) == true }
            .mapNotNull { dataSnapshot -> dataSnapshot.key }
    }

    override suspend fun addGroup(studentId: String, groupId: String) {
        studentsGroups.child(studentId).child(groupId).setValue(true)
    }

    override suspend fun removeGroup(studentId: String, groupId: String) {
        studentsGroups.child(studentId).child(groupId).removeValue()
    }

    override suspend fun removeAllGroups(studentId: String) {
        studentsGroups.child(studentId).removeValue()
    }
}