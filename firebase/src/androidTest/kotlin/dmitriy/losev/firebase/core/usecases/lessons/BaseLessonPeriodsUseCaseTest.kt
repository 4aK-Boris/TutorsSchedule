package dmitriy.losev.firebase.core.usecases.lessons

import com.google.firebase.database.DatabaseReference
import dmitriy.losev.firebase.core.BaseUseCaseTest
import dmitriy.losev.firebase.core.LESSONS
import dmitriy.losev.firebase.core.PERIODS
import kotlinx.coroutines.tasks.await
import org.koin.test.inject

abstract class BaseLessonPeriodsUseCaseTest : BaseUseCaseTest() {

    private val reference by inject<DatabaseReference>()

    private val periodsReference by lazy { reference.child(LESSONS).child(LESSON_ID).child(PERIODS) }

    protected suspend fun addPeriod() {
        addPeriod(id = PERIOD_ID)
    }

    protected suspend fun addPeriod(id: String) {
        periodsReference.child(id).setValue(true).await()
    }

    protected suspend fun addPeriods(count: Int) {
        repeat(count) { index ->
            addPeriod(id = "$PERIOD_ID-$index")
        }
    }

    protected suspend fun deletePeriods() {
        periodsReference.removeValue().await()
    }

    protected suspend fun getPeriod(): String? {
        return getPeriod(key = PERIOD_ID)
    }

    protected suspend fun getPeriod(key: String): String? {
        val hasPeriodInLesson = periodsReference.child(key).get().await().getValue(Boolean::class.java)
        return if (hasPeriodInLesson == true) {
            periodsReference.child(key).key
        } else {
            null
        }
    }

    protected suspend fun hasPeriod(): Boolean {
        return periodsReference.get().await().children.find { dataSnapshot ->
            dataSnapshot.key == PERIOD_ID && dataSnapshot.getValue(Boolean::class.java) == true
        } != null
    }

    protected suspend fun hasPeriods(): Boolean {
        return periodsReference.get().await().children.toList().isNotEmpty()
    }

    companion object {

        const val LESSON_ID = "4324324324324234v8324324324v32"
        const val PERIOD_ID = "d9c4983m24382c7432m748320-432"
    }
}