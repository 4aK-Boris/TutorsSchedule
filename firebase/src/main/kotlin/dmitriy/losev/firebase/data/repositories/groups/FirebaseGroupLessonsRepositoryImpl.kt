package dmitriy.losev.firebase.data.repositories.groups

import com.google.firebase.database.DatabaseReference
import dmitriy.losev.firebase.core.GROUPS
import dmitriy.losev.firebase.core.LESSONS
import dmitriy.losev.firebase.domain.repositories.groups.FirebaseGroupLessonsRepository
import kotlinx.coroutines.tasks.await

class FirebaseGroupLessonsRepositoryImpl(reference: DatabaseReference): FirebaseGroupLessonsRepository {

    private val groupLessons by lazy { reference.child(GROUPS) }
    override suspend fun getAllLessons(groupId: String): List<String> {
        return groupLessons.child(groupId).child(LESSONS).get().await().children
            .filter { dataSnapshot -> dataSnapshot.getValue(Boolean::class.java) == true }
            .mapNotNull { dataSnapshot -> dataSnapshot.key }
    }

    override suspend fun addLesson(groupId: String, lessonId: String) {
        groupLessons.child(groupId).child(LESSONS).child(lessonId).setValue(true).await()
    }

    override suspend fun removeLesson(groupId: String, lessonId: String) {
        groupLessons.child(groupId).child(LESSONS).child(lessonId).removeValue().await()
    }

    override suspend fun removeAllLessons(groupId: String) {
        groupLessons.child(groupId).child(LESSONS).removeValue().await()
    }
}