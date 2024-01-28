package dmitriy.losev.students.domain.usecases.lessons

import dmitriy.losev.firebase.domain.models.Lesson
import dmitriy.losev.firebase.domain.usecases.lessons.FirebaseGetLessonUseCase
import dmitriy.losev.firebase.domain.usecases.students.lessons.FirebaseGetLimitStudentLessonsUseCase
import dmitriy.losev.students.core.LESSONS_LIMIT
import dmitriy.losev.students.core.StudentsBaseUseCase

class GetStudentLessonsUseCase(
    private val firebaseGetLimitStudentLessonsUseCase: FirebaseGetLimitStudentLessonsUseCase,
    private val firebaseGetLessonUseCase: FirebaseGetLessonUseCase
) : StudentsBaseUseCase() {

    suspend fun getStudentLessons(studentId: String): List<Lesson> {
        return firebaseGetLimitStudentLessonsUseCase.getLimitLessons(studentId = studentId, count = LESSONS_LIMIT).map { lessonId ->
            firebaseGetLessonUseCase.getLesson(lessonId)
        }
    }
}