package dmitriy.losev.firebase.domain.usecases.groups

import dmitriy.losev.firebase.core.FirebaseBaseUseCase
import dmitriy.losev.firebase.core.exception.GroupAddException
import dmitriy.losev.core.models.Group
import dmitriy.losev.firebase.domain.repositories.groups.FirebaseGroupsRepository
import dmitriy.losev.firebase.domain.usecases.user.FirebaseGetUserUseCase

class FirebaseAddGroupUseCase(
    private val firebaseGroupsRepository: FirebaseGroupsRepository,
    private val firebaseGetUserUseCase: FirebaseGetUserUseCase
): FirebaseBaseUseCase() {

    suspend fun addGroup(group: Group): String {
        val user = firebaseGetUserUseCase.getUserWithException()
        return firebaseGroupsRepository.addGroup(teacherId = user.uid, group = group) ?: throw GroupAddException()
    }
}