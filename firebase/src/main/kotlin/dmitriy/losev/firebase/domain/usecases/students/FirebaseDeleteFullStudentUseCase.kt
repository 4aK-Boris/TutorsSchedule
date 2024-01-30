package dmitriy.losev.firebase.domain.usecases.students

import dmitriy.losev.firebase.core.FirebaseBaseUseCase
import dmitriy.losev.firebase.domain.usecases.contacts.FirebaseDeleteContactsUseCase
import dmitriy.losev.firebase.domain.usecases.groups.students.FirebaseRemoveGroupStudentUseCase
import dmitriy.losev.firebase.domain.usecases.lessons.FirebaseDeleteFullLessonUseCase
import dmitriy.losev.firebase.domain.usecases.students.groups.FirebaseGetStudentGroupIdsUseCase
import dmitriy.losev.firebase.domain.usecases.students.lessons.FirebaseGetAllStudentLessonsUseCase

class FirebaseDeleteFullStudentUseCase(
    private val firebaseGetAllStudentLessonsUseCase: FirebaseGetAllStudentLessonsUseCase,
    private val firebaseGetStudentGroupIdsUseCase: FirebaseGetStudentGroupIdsUseCase,
    private val firebaseDeleteStudentUseCase: FirebaseDeleteStudentUseCase,
    private val firebaseDeleteFullLessonUseCase: FirebaseDeleteFullLessonUseCase,
    private val firebaseDeleteContactsUseCase: FirebaseDeleteContactsUseCase,
    private val firebaseRemoveGroupStudentUseCase: FirebaseRemoveGroupStudentUseCase
): FirebaseBaseUseCase() {

    suspend fun deleteFullStudent(studentId: String) {
        launchFun(
            f1 = { deleteAllLessons(studentId) },
            f2 = { deleteStudentFromGroup(studentId) },
            f3 = { firebaseDeleteStudentUseCase.deleteStudent(studentId) },
            f4 = { firebaseDeleteContactsUseCase.deleteContacts(studentId) }
        )
    }

    private suspend fun deleteStudentFromGroup(studentId: String) {
        firebaseGetStudentGroupIdsUseCase.getGroupIds(studentId).forEach { groupId ->
            firebaseRemoveGroupStudentUseCase.removeStudent(groupId, studentId)
        }
    }

    private suspend fun deleteAllLessons(studentId: String) {
        firebaseGetAllStudentLessonsUseCase.getAllLessons(studentId).forEach { lessonId ->
            firebaseDeleteFullLessonUseCase.deleteFullLesson(lessonId)
        }
    }
}