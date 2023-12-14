package dmitriy.losev.firebase.domain.usecases.groups.lessons

import dmitriy.losev.firebase.core.FirebaseBaseUseCase
import dmitriy.losev.firebase.domain.repositories.groups.FirebaseGroupLessonsRepository

class FirebaseRemoveAllGroupLessonsUseCase(private val firebaseGroupLessonsRepository: FirebaseGroupLessonsRepository) : FirebaseBaseUseCase() {

    suspend fun removeAllLessons(groupId: String) {
        firebaseGroupLessonsRepository.removeAllLessons(groupId)
    }
}