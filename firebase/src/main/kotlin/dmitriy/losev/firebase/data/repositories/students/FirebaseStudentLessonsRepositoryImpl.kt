package dmitriy.losev.firebase.data.repositories.students

import com.google.firebase.database.DatabaseReference
import dmitriy.losev.firebase.core.LESSONS
import dmitriy.losev.firebase.core.STUDENTS
import dmitriy.losev.firebase.domain.repositories.students.FirebaseStudentLessonsRepository
import kotlinx.coroutines.tasks.await

class FirebaseStudentLessonsRepositoryImpl(reference: DatabaseReference): FirebaseStudentLessonsRepository {

    private val students by lazy { reference.child(STUDENTS) }
    override suspend fun getAllLessons(studentId: String): List<String> {
        return students.child(studentId).child(LESSONS).get().await().children
            .filter { dataSnapshot -> dataSnapshot.getValue(Boolean::class.java) == true }
            .mapNotNull { dataSnapshot -> dataSnapshot.key }
    }

    override suspend fun getLimitLessons(studentId: String, count: Int): List<String> {
        return students.child(studentId).child(LESSONS).limitToFirst(count).get().await().children
            .filter { dataSnapshot -> dataSnapshot.getValue(Boolean::class.java) == true }
            .mapNotNull { dataSnapshot -> dataSnapshot.key }
    }

    override suspend fun addLesson(studentId: String, lessonId: String) {
        students.child(studentId).child(LESSONS).child(lessonId).setValue(true)
    }

    override suspend fun removeLesson(studentId: String, lessonId: String) {
        students.child(studentId).child(LESSONS).child(lessonId).removeValue()
    }

    override suspend fun removeAllLessons(studentId: String) {
        students.child(studentId).child(LESSONS).removeValue()
    }
}