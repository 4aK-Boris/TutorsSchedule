package dmitriy.losev.firebase.domain.usecases.groups

import dmitriy.losev.firebase.core.FirebaseBaseUseCase
import dmitriy.losev.firebase.domain.repositories.groups.FirebaseGroupsRepository
import dmitriy.losev.firebase.domain.usecases.user.FirebaseGetUserUseCase

class FirebaseDeleteGroupUseCase(
    private val firebaseGroupsRepository: FirebaseGroupsRepository,
    private val firebaseGetUserUseCase: FirebaseGetUserUseCase
) : FirebaseBaseUseCase() {

    suspend fun deleteGroup(groupId: String) {
        val user = firebaseGetUserUseCase.getUserWithException()
        return firebaseGroupsRepository.deleteGroup(teacherId = user.uid, groupId = groupId)
    }
}