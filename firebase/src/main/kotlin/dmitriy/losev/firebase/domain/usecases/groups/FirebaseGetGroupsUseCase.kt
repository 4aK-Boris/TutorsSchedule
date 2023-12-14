package dmitriy.losev.firebase.domain.usecases.groups

import dmitriy.losev.firebase.core.FirebaseBaseUseCase
import dmitriy.losev.firebase.domain.models.Group
import dmitriy.losev.firebase.domain.repositories.groups.FirebaseGroupsRepository
import dmitriy.losev.firebase.domain.usecases.user.FirebaseGetUserUseCase

class FirebaseGetGroupsUseCase(
    private val firebaseGroupsRepository: FirebaseGroupsRepository,
    private val firebaseGetUserUseCase: FirebaseGetUserUseCase
): FirebaseBaseUseCase() {

    suspend fun getGroups(): List<Group> {
        val user = firebaseGetUserUseCase.getUserWithException()
        return firebaseGroupsRepository.getGroups(teacherId = user.uid)
    }
}