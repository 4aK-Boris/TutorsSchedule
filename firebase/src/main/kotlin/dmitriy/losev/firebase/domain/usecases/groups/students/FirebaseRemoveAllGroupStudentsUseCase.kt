package dmitriy.losev.firebase.domain.usecases.groups.students

import dmitriy.losev.firebase.core.FirebaseBaseUseCase
import dmitriy.losev.firebase.domain.repositories.groups.FirebaseGroupStudentsRepository

class FirebaseRemoveAllGroupStudentsUseCase(private val firebaseGroupStudentsRepository: FirebaseGroupStudentsRepository) : FirebaseBaseUseCase() {

    suspend fun removeAllStudents(groupId: String) {
        firebaseGroupStudentsRepository.removeAllStudents(groupId)
    }
}