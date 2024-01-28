package dmitriy.losev.firebase.domain.usecases.lessons

import dmitriy.losev.firebase.core.FirebaseBaseUseCase
import dmitriy.losev.firebase.domain.usecases.lessons.periods.FirebaseGetAllLessonPeriodsUseCase
import dmitriy.losev.firebase.domain.usecases.periods.FirebaseDeleteFullPeriodUseCase

class FirebaseDeleteFullLessonUseCase(
    private val firebaseGetAllLessonPeriodsUseCase: FirebaseGetAllLessonPeriodsUseCase,
    private val firebaseDeleteLessonUseCase: FirebaseDeleteLessonUseCase,
    private val firebaseDeleteFullPeriodUseCase: FirebaseDeleteFullPeriodUseCase
): FirebaseBaseUseCase() {

    suspend fun deleteFullLesson(lessonId: String) {
        launchFun(
            f1 = { deleteAllPeriods(lessonId) },
            f2 = { firebaseDeleteLessonUseCase.deleteLesson(lessonId) }
        )
    }

    private suspend fun deleteAllPeriods(lessonId: String) {
        firebaseGetAllLessonPeriodsUseCase.getAllPeriods(lessonId).forEach { periodId ->
            firebaseDeleteFullPeriodUseCase.deleteFullPeriod(periodId)
        }
    }
}