package dmitriy.losev.firebase.core.usecases.groups

import com.google.firebase.database.DatabaseReference
import dmitriy.losev.firebase.core.BaseUseCaseTest
import dmitriy.losev.firebase.core.GROUPS
import dmitriy.losev.firebase.core.STUDENTS
import kotlinx.coroutines.tasks.await
import org.koin.test.inject

abstract class BaseGroupStudentsUseCaseTest : BaseUseCaseTest() {

    private val reference by inject<DatabaseReference>()

    private val groupStudentsReference by lazy { reference.child(GROUPS).child(STUDENTS) }

    protected suspend fun addStudent() {
        addStudent(id = STUDENT_ID)
    }

    protected suspend fun addStudent(id: String) {
        groupStudentsReference.child(GROUP_ID).child(id).setValue(true).await()
    }

    protected suspend fun addStudents(count: Int) {
        repeat(count) { index ->
            addStudent(id = "$STUDENT_ID-$index")
        }
    }

    protected suspend fun deleteStudents() {
        groupStudentsReference.child(GROUP_ID).removeValue().await()
    }

    protected suspend fun getStudent(): String? {
        return getStudent(key = STUDENT_ID)
    }

    private suspend fun getStudent(key: String): String? {
        val hasStudentInGroup = groupStudentsReference.child(GROUP_ID).child(key).get().await().getValue(Boolean::class.java)
        return if (hasStudentInGroup == true) {
            groupStudentsReference.child(GROUP_ID).child(key).key
        } else {
            null
        }
    }

    protected suspend fun hasStudent(): Boolean {
        return groupStudentsReference.child(GROUP_ID).get().await().children.find { dataSnapshot ->
            dataSnapshot.key == STUDENT_ID && dataSnapshot.getValue(Boolean::class.java) == true
        } != null
    }

    protected suspend fun hasStudents(): Boolean {
        return groupStudentsReference.child(GROUP_ID).get().await().children.toList().isNotEmpty()
    }

    companion object {

        const val GROUP_ID = "4324324324324234v8324324324v32"
        const val STUDENT_ID = "d9c4983m24382c7432m748320-432"
    }
}