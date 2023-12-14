package dmitriy.losev.firebase.repositories.lessons

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.firebase.database.DatabaseReference
import dmitriy.losev.firebase.core.BaseRepositoryTest
import dmitriy.losev.firebase.core.LESSONS
import dmitriy.losev.firebase.core.PERIODS
import dmitriy.losev.firebase.domain.repositories.lessons.FirebaseLessonPeriodsRepository
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.tasks.await
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.test.inject
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

@RunWith(AndroidJUnit4::class)
class FirebaseLessonPeriodsRepositoryTest : BaseRepositoryTest() {

    private val reference by inject<DatabaseReference>()

    private val lessonPeriodsReference by lazy { reference.child(LESSONS).child(PERIODS) }

    private val firebaseLessonPeriodsRepository by inject<FirebaseLessonPeriodsRepository>()

    override suspend fun tearDown() {
        deletePeriods()
    }

    @Test
    fun testGetAllPeriods(): Unit = runBlocking {

        val count = 10

        addPeriods(count = count)

        val actualResult = firebaseLessonPeriodsRepository.getAllPeriods(LESSON_ID)

        assertEquals(count, actualResult.size)

        actualResult.forEachIndexed { index, periodId ->
            assertEquals("${PERIOD_ID}-$index", periodId)
        }
    }

    @Test
    fun testAddPeriod(): Unit = runBlocking {

        firebaseLessonPeriodsRepository.addPeriod(LESSON_ID, PERIOD_ID)

        val hasPeriod = hasPeriod()

        assertTrue(hasPeriod)

        val actualResult = getPeriod()

        assertEquals(PERIOD_ID, actualResult)
    }

    @Test
    fun testRemovePeriod(): Unit = runBlocking {

        addPeriod()

        var hasPeriod = hasPeriod()

        assertTrue(hasPeriod)

        firebaseLessonPeriodsRepository.removePeriod(LESSON_ID, PERIOD_ID)

        hasPeriod = hasPeriod()

        assertFalse(hasPeriod)
    }

    @Test
    fun removeAllPeriods(): Unit = runBlocking {

        val count = 10

        addPeriods(count)

        var hasPeriods = hasPeriods()

        assertTrue(hasPeriods)

        firebaseLessonPeriodsRepository.removeAllPeriods(LESSON_ID)

        hasPeriods = hasPeriods()

        assertFalse(hasPeriods)
    }


    private suspend fun addPeriod() {
        addPeriod(id = PERIOD_ID)
    }

    private suspend fun addPeriod(id: String) {
        lessonPeriodsReference.child(LESSON_ID).child(id).setValue(true).await()
    }

    private suspend fun addPeriods(count: Int) {
        repeat(count) { index ->
            addPeriod(id = "$PERIOD_ID-$index")
        }
    }

    private suspend fun deletePeriods() {
        lessonPeriodsReference.child(LESSON_ID).removeValue().await()
    }

    private suspend fun getPeriod(): String? {
        return getPeriod(key = PERIOD_ID)
    }

    private suspend fun getPeriod(key: String): String? {
        val hasPeriodInLesson = lessonPeriodsReference.child(LESSON_ID).child(key).get().await().getValue(Boolean::class.java)
        return if (hasPeriodInLesson == true) {
            lessonPeriodsReference.child(LESSON_ID).child(key).key
        } else {
            null
        }
    }

    private suspend fun hasPeriod(): Boolean {
        return lessonPeriodsReference.child(LESSON_ID).get().await().children.find { dataSnapshot ->
            dataSnapshot.key == PERIOD_ID && dataSnapshot.getValue(Boolean::class.java) == true
        } != null
    }

    private suspend fun hasPeriods(): Boolean {
        return lessonPeriodsReference.child(LESSON_ID).get().await().children.toList().isNotEmpty()
    }

    companion object {

        private const val LESSON_ID = "4324324324324234v8324324324v32"
        private const val PERIOD_ID = "d9c4983m24382c7432m748320-432"
    }
}