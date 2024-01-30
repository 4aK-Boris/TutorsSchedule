package dmitriy.losev.firebase.data.repositories.groups

import com.google.firebase.database.DatabaseReference
import dmitriy.losev.firebase.core.GROUPS
import dmitriy.losev.firebase.core.LESSONS
import dmitriy.losev.firebase.domain.repositories.groups.FirebaseGroupLessonsRepository
import kotlinx.coroutines.tasks.await

class FirebaseGroupLessonsRepositoryImpl(private val reference: DatabaseReference) : FirebaseGroupLessonsRepository {

    override suspend fun getAllLessons(teacherId: String, groupId: String): List<String> {
        return getGroupLessonsReference(teacherId, groupId).get().await().children
            .filter { dataSnapshot -> dataSnapshot.getValue(Boolean::class.java) == true }
            .mapNotNull { dataSnapshot -> dataSnapshot.key }
    }

    override suspend fun addLesson(teacherId: String, groupId: String, lessonId: String) {
        getGroupLessonsReference(teacherId, groupId).child(lessonId).setValue(true).await()
    }

    override suspend fun removeLesson(teacherId: String, groupId: String, lessonId: String) {
        getGroupLessonsReference(teacherId, groupId).child(lessonId).removeValue().await()
    }

    override suspend fun removeAllLessons(teacherId: String, groupId: String) {
        getGroupLessonsReference(teacherId, groupId).removeValue().await()
    }

    private fun getGroupLessonsReference(teacherId: String, groupId: String): DatabaseReference {
        return reference.child(teacherId).child(GROUPS).child(groupId).child(LESSONS)
    }
}