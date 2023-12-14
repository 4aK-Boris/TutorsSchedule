package dmitriy.losev.firebase.core.usecases.students

import com.google.firebase.database.DatabaseReference
import dmitriy.losev.firebase.core.BaseUseCaseTest
import dmitriy.losev.firebase.core.LESSONS
import dmitriy.losev.firebase.core.STUDENTS
import kotlinx.coroutines.tasks.await
import org.koin.test.inject

abstract class BaseStudentLessonsUseCaseTest : BaseUseCaseTest() {

    private val reference by inject<DatabaseReference>()

    private val studentLessonsReference by lazy { reference.child(STUDENTS).child(LESSONS) }

    protected suspend fun addLesson() {
        addLesson(id = LESSON_ID)
    }

    private suspend fun addLesson(id: String) {
        studentLessonsReference.child(STUDENT_ID).child(id).setValue(true).await()
    }

    protected suspend fun addLessons(count: Int) {
        repeat(count) { index ->
            addLesson(id = "$LESSON_ID-$index")
        }
    }

    protected suspend fun deleteLessons() {
        studentLessonsReference.child(STUDENT_ID).removeValue().await()
    }

    protected suspend fun getLesson(): String? {
        return getLesson(key = LESSON_ID)
    }

    private suspend fun getLesson(key: String): String? {
        val hasLessonInStudent = studentLessonsReference.child(STUDENT_ID).child(key).get().await().getValue(Boolean::class.java)
        return if (hasLessonInStudent == true) {
            studentLessonsReference.child(STUDENT_ID).child(key).key
        } else {
            null
        }
    }

    protected suspend fun hasLesson(): Boolean {
        return studentLessonsReference.child(STUDENT_ID).get().await().children.find { dataSnapshot ->
            dataSnapshot.key == LESSON_ID && dataSnapshot.getValue(Boolean::class.java) == true
        } != null
    }

    protected suspend fun hasLessons(): Boolean {
        return studentLessonsReference.child(STUDENT_ID).get().await().children.toList().isNotEmpty()
    }

    companion object {

        const val STUDENT_ID = "4324324324324234v8324324324v32"
        const val LESSON_ID = "d9c4983m24382c7432m748320-432"
    }
}