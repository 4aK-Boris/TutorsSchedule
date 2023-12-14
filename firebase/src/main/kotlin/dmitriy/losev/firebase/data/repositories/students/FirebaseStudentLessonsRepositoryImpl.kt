package dmitriy.losev.firebase.data.repositories.students

import com.google.firebase.database.DatabaseReference
import dmitriy.losev.firebase.core.LESSONS
import dmitriy.losev.firebase.core.STUDENTS
import dmitriy.losev.firebase.domain.repositories.students.FirebaseStudentLessonsRepository
import kotlinx.coroutines.tasks.await

class FirebaseStudentLessonsRepositoryImpl(reference: DatabaseReference): FirebaseStudentLessonsRepository {

    private val studentsLessons by lazy { reference.child(STUDENTS).child(LESSONS) }
    override suspend fun getAllLessons(studentId: String): List<String> {
        return studentsLessons.child(studentId).get().await().children
            .filter { dataSnapshot -> dataSnapshot.getValue(Boolean::class.java) == true }
            .mapNotNull { dataSnapshot -> dataSnapshot.key }
    }

    override suspend fun addLesson(studentId: String, lessonId: String) {
        studentsLessons.child(studentId).child(lessonId).setValue(true)
    }

    override suspend fun removeLesson(studentId: String, lessonId: String) {
        studentsLessons.child(studentId).child(lessonId).removeValue()
    }

    override suspend fun removeAllLessons(studentId: String) {
        studentsLessons.child(studentId).removeValue()
    }
}