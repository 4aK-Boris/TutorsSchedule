package dmitriy.losev.firebase.data.repositories.groups

import com.google.firebase.database.DatabaseReference
import dmitriy.losev.firebase.core.GROUPS
import dmitriy.losev.firebase.core.STUDENTS
import dmitriy.losev.firebase.domain.repositories.groups.FirebaseGroupStudentsRepository
import kotlinx.coroutines.tasks.await

class FirebaseGroupStudentsRepositoryImpl(private val reference: DatabaseReference): FirebaseGroupStudentsRepository {

    override suspend fun getStudents(teacherId: String, groupId: String): List<String> {
        return getGroupStudentsReference(teacherId, groupId).get().await().children
            .filter { dataSnapshot -> dataSnapshot.getValue(Boolean::class.java) == true }
            .mapNotNull { dataSnapshot -> dataSnapshot.key }
    }

    override suspend fun addStudent(teacherId: String, groupId: String, studentId: String) {
        getGroupStudentsReference(teacherId, groupId).child(studentId).setValue(true).await()
    }

    override suspend fun removeStudent(teacherId: String, groupId: String, studentId: String) {
        getGroupStudentsReference(teacherId, groupId).child(studentId).removeValue().await()
    }

    override suspend fun removeStudents(teacherId: String, groupId: String) {
        getGroupStudentsReference(teacherId, groupId).removeValue().await()
    }

    private fun getGroupStudentsReference(teacherId: String, groupId: String): DatabaseReference {
        return reference.child(teacherId).child(GROUPS).child(groupId).child(STUDENTS)
    }
}