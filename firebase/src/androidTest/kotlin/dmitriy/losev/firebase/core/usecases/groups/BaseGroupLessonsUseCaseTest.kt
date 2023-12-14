package dmitriy.losev.firebase.core.usecases.groups

import com.google.firebase.database.DatabaseReference
import dmitriy.losev.firebase.core.BaseUseCaseTest
import dmitriy.losev.firebase.core.GROUPS
import dmitriy.losev.firebase.core.LESSONS
import kotlinx.coroutines.tasks.await
import org.koin.test.inject

abstract class BaseGroupLessonsUseCaseTest : BaseUseCaseTest() {

    private val reference by inject<DatabaseReference>()

    private val groupLessonsReference by lazy { reference.child(GROUPS).child(LESSONS) }

    protected suspend fun addLesson() {
        addLesson(id = LESSON_ID)
    }

    private suspend fun addLesson(id: String) {
        groupLessonsReference.child(GROUP_ID).child(id).setValue(true).await()
    }

    protected suspend fun addLessons(count: Int) {
        repeat(count) { index ->
            addLesson(id = "$LESSON_ID-$index")
        }
    }

    protected suspend fun deleteLessons() {
        groupLessonsReference.child(GROUP_ID).removeValue().await()
    }

    protected suspend fun getLesson(): String? {
        return getLesson(key = LESSON_ID)
    }

    private suspend fun getLesson(key: String): String? {
        val hasLessonInGroup = groupLessonsReference.child(GROUP_ID).child(key).get().await().getValue(Boolean::class.java)
        return if (hasLessonInGroup == true) {
            groupLessonsReference.child(GROUP_ID).child(key).key
        } else {
            null
        }
    }

    protected suspend fun hasLesson(): Boolean {
        return groupLessonsReference.child(GROUP_ID).get().await().children.find { dataSnapshot ->
            dataSnapshot.key == LESSON_ID && dataSnapshot.getValue(Boolean::class.java) == true
        } != null
    }

    protected suspend fun hasLessons(): Boolean {
        return groupLessonsReference.child(GROUP_ID).get().await().children.toList().isNotEmpty()
    }

    companion object {

        const val GROUP_ID = "4324324324324234v8324324324v32"
        const val LESSON_ID = "d9c4983m24382c7432m748320-432"
    }
}