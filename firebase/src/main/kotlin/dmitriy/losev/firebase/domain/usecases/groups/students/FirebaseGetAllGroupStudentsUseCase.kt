package dmitriy.losev.firebase.domain.usecases.groups.students

import dmitriy.losev.firebase.core.FirebaseBaseUseCase
import dmitriy.losev.firebase.domain.repositories.groups.FirebaseGroupStudentsRepository

class FirebaseGetAllGroupStudentsUseCase(private val firebaseGroupStudentsRepository: FirebaseGroupStudentsRepository) : FirebaseBaseUseCase() {

    suspend fun getAllStudents(groupId: String): List<String> {
        return firebaseGroupStudentsRepository.getAllStudents(groupId)
    }
}