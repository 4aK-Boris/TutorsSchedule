package dmitriy.losev.firebase.domain.usecases.groups.lessons

import dmitriy.losev.firebase.core.FirebaseBaseUseCase
import dmitriy.losev.firebase.domain.repositories.groups.FirebaseGroupLessonsRepository

class FirebaseGetAllGroupLessonsUseCase(private val firebaseGroupLessonsRepository: FirebaseGroupLessonsRepository) : FirebaseBaseUseCase() {

    suspend fun getAllLessons(groupId: String): List<String> {
        return firebaseGroupLessonsRepository.getAllLessons(groupId)
    }
}