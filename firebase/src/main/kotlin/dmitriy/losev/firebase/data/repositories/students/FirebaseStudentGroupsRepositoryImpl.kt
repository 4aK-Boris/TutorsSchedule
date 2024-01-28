package dmitriy.losev.firebase.data.repositories.students

import com.google.firebase.database.DatabaseReference
import dmitriy.losev.firebase.core.GROUPS
import dmitriy.losev.firebase.core.STUDENTS
import dmitriy.losev.firebase.domain.repositories.students.FirebaseStudentGroupsRepository
import kotlinx.coroutines.tasks.await

class FirebaseStudentGroupsRepositoryImpl(reference: DatabaseReference) : FirebaseStudentGroupsRepository {

    private val students by lazy { reference.child(STUDENTS) }
    override suspend fun getAllGroups(studentId: String): List<String> {
        return students.child(studentId).child(GROUPS).get().await().children
            .filter { dataSnapshot -> dataSnapshot.getValue(Boolean::class.java) == true }
            .mapNotNull { dataSnapshot -> dataSnapshot.key }

    }

    override suspend fun getLimitGroups(studentId: String, count: Int): List<String> {
        return students.child(studentId).child(GROUPS).limitToFirst(count).get().await().children
            .filter { dataSnapshot -> dataSnapshot.getValue(Boolean::class.java) == true }
            .mapNotNull { dataSnapshot -> dataSnapshot.key }
    }

    override suspend fun addGroup(studentId: String, groupId: String) {
        students.child(studentId).child(GROUPS).child(groupId).setValue(true)
    }

    override suspend fun removeGroup(studentId: String, groupId: String) {
        students.child(studentId).child(GROUPS).child(groupId).removeValue()
    }

    override suspend fun removeAllGroups(studentId: String) {
        students.child(studentId).child(GROUPS).removeValue()
    }
}