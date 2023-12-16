package dmitriy.losev.firebase.core.usecases.students

import com.google.firebase.database.DatabaseReference
import dmitriy.losev.firebase.core.BaseUseCaseTest
import dmitriy.losev.firebase.core.GROUPS
import dmitriy.losev.firebase.core.STUDENTS
import kotlinx.coroutines.tasks.await
import org.koin.test.inject

abstract class BaseStudentGroupsUseCaseTest : BaseUseCaseTest() {

    private val reference by inject<DatabaseReference>()

    private val groupsReference by lazy { reference.child(STUDENTS).child(STUDENT_ID).child(GROUPS) }

    protected suspend fun addGroup() {
        addGroup(id = GROUP_ID)
    }

    protected suspend fun addGroup(id: String) {
        groupsReference.child(id).setValue(true).await()
    }

    protected suspend fun addGroups(count: Int) {
        repeat(count) { index ->
            addGroup(id = "$GROUP_ID-$index")
        }
    }

    protected suspend fun deleteGroups() {
        groupsReference.removeValue().await()
    }

    protected suspend fun getGroup(): String? {
        return getGroup(key = GROUP_ID)
    }

    protected suspend fun getGroup(key: String): String? {
        val hasGroupInStudent = groupsReference.child(key).get().await().getValue(Boolean::class.java)
        return if (hasGroupInStudent == true) {
            groupsReference.child(key).key
        } else {
            null
        }
    }

    protected suspend fun hasGroup(): Boolean {
        return groupsReference.get().await().children.find { dataSnapshot ->
            dataSnapshot.key == GROUP_ID && dataSnapshot.getValue(Boolean::class.java) == true
        } != null
    }

    protected suspend fun hasGroups(): Boolean {
        return groupsReference.get().await().children.toList().isNotEmpty()
    }

    companion object {

        const val STUDENT_ID = "4324324324324234v8324324324v32"
        const val GROUP_ID = "d9c4983m24382c7432m748320-432"
    }
}