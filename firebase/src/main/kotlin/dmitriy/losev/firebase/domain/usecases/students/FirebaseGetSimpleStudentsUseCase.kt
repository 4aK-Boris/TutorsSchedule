package dmitriy.losev.firebase.domain.usecases.students

import dmitriy.losev.firebase.core.FirebaseBaseUseCase
import dmitriy.losev.firebase.domain.models.SimpleStudent
import dmitriy.losev.firebase.domain.repositories.students.FirebaseStudentsRepository
import dmitriy.losev.firebase.domain.usecases.user.FirebaseGetUserUseCase

class FirebaseGetSimpleStudentsUseCase(
    private val firebaseStudentsRepository: FirebaseStudentsRepository,
    private val firebaseGetUserUseCase: FirebaseGetUserUseCase
): FirebaseBaseUseCase() {

    suspend fun getSimpleStudents(): List<SimpleStudent> {
        val user = firebaseGetUserUseCase.getUserWithException()
        return firebaseStudentsRepository.getSimpleStudents(teacherUid = user.uid)
    }
}