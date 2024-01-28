package dmitriy.losev.firebase.domain.usecases.groups

import dmitriy.losev.firebase.core.FirebaseBaseUseCase
import dmitriy.losev.firebase.domain.models.Group
import dmitriy.losev.firebase.domain.repositories.groups.FirebaseGroupsRepository
import dmitriy.losev.firebase.domain.usecases.user.FirebaseGetUserUseCase

class FirebaseUpdateGroupUseCase(
    private val firebaseGroupsRepository: FirebaseGroupsRepository,
    private val firebaseGetUserUseCase: FirebaseGetUserUseCase
): FirebaseBaseUseCase() {

    suspend fun updateGroup(groupId: String, group: Group) {
        val user = firebaseGetUserUseCase.getUserWithException()
        return firebaseGroupsRepository.updateGroup(teacherId = user.uid, groupId = groupId, group = group)
    }
}