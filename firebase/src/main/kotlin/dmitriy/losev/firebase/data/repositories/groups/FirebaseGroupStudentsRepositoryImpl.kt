package dmitriy.losev.firebase.data.repositories.groups

import com.google.firebase.database.DatabaseReference
import dmitriy.losev.firebase.core.GROUPS
import dmitriy.losev.firebase.core.STUDENTS
import dmitriy.losev.firebase.domain.repositories.groups.FirebaseGroupStudentsRepository
import kotlinx.coroutines.tasks.await

class FirebaseGroupStudentsRepositoryImpl(reference: DatabaseReference): FirebaseGroupStudentsRepository {

    private val groupStudents by lazy { reference.child(GROUPS) }
    override suspend fun getAllStudents(groupId: String): List<String> {
        return groupStudents.child(groupId).child(STUDENTS).get().await().children
            .filter { dataSnapshot -> dataSnapshot.getValue(Boolean::class.java) == true }
            .mapNotNull { dataSnapshot -> dataSnapshot.key }
    }

    override suspend fun addStudent(groupId: String, studentId: String) {
        groupStudents.child(groupId).child(STUDENTS).child(studentId).setValue(true).await()
    }

    override suspend fun removeStudent(groupId: String, studentId: String) {
        groupStudents.child(groupId).child(STUDENTS).child(studentId).removeValue().await()
    }

    override suspend fun removeAllStudents(groupId: String) {
        groupStudents.child(groupId).child(STUDENTS).removeValue().await()
    }
}