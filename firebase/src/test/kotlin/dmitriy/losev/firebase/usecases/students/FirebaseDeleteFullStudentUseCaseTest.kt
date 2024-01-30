package dmitriy.losev.firebase.usecases.students

import dmitriy.losev.firebase.domain.usecases.groups.students.FirebaseRemoveGroupStudentUseCase
import dmitriy.losev.firebase.domain.usecases.lessons.FirebaseDeleteFullLessonUseCase
import dmitriy.losev.firebase.domain.usecases.students.FirebaseDeleteFullStudentUseCase
import dmitriy.losev.firebase.domain.usecases.students.FirebaseDeleteStudentUseCase
import dmitriy.losev.firebase.domain.usecases.students.groups.FirebaseGetStudentGroupIdsUseCase
import dmitriy.losev.firebase.domain.usecases.students.lessons.FirebaseGetAllStudentLessonsUseCase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test

class FirebaseDeleteFullStudentUseCaseTest {

    private val firebaseGetAllStudentLessonsUseCase = mockk<FirebaseGetAllStudentLessonsUseCase>()
    private val firebaseGetStudentGroupIdsUseCase = mockk<FirebaseGetStudentGroupIdsUseCase>()
    private val firebaseDeleteStudentUseCase = mockk<FirebaseDeleteStudentUseCase>(relaxed = true)
    private val firebaseDeleteFullLessonUseCase = mockk<FirebaseDeleteFullLessonUseCase>(relaxed = true)
    private val firebaseRemoveGroupStudentUseCase = mockk<FirebaseRemoveGroupStudentUseCase>(relaxed = true)

    private val groups = listOf(GROUP_ID, GROUP_ID, GROUP_ID)
    private val lessons = listOf(LESSON_ID, LESSON_ID, LESSON_ID)

    private val firebaseDeleteFullStudentUseCase = FirebaseDeleteFullStudentUseCase(
        firebaseGetAllStudentLessonsUseCase,
        firebaseGetStudentGroupIdsUseCase,
        firebaseDeleteStudentUseCase,
        firebaseDeleteFullLessonUseCase,
        firebaseRemoveGroupStudentUseCase
    )

    @Test
    fun testDeleteFullStudent() = runBlocking {

        coEvery { firebaseGetStudentGroupIdsUseCase.getGroupIds(STUDENT_ID) } returns groups
        coEvery { firebaseGetAllStudentLessonsUseCase.getAllLessons(STUDENT_ID) } returns lessons

        firebaseDeleteFullStudentUseCase.deleteFullStudent(STUDENT_ID)

        coVerify { firebaseGetStudentGroupIdsUseCase.getGroupIds(STUDENT_ID) }
        coVerify { firebaseGetAllStudentLessonsUseCase.getAllLessons(STUDENT_ID) }
        coVerify(exactly = SIZE) { firebaseRemoveGroupStudentUseCase.removeStudent(GROUP_ID, STUDENT_ID) }
        coVerify(exactly = SIZE) { firebaseDeleteFullLessonUseCase.deleteFullLesson(LESSON_ID) }
        coVerify { firebaseDeleteStudentUseCase.deleteStudent(STUDENT_ID) }
    }

    companion object {

        private const val SIZE = 3

        private const val LESSON_ID = "lesson_id"
        private const val GROUP_ID = "group_id"
        private const val STUDENT_ID = "student_id"
    }
}