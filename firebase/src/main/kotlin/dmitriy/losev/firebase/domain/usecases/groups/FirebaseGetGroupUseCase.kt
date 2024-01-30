package dmitriy.losev.firebase.domain.usecases.groups

import dmitriy.losev.firebase.core.FirebaseBaseUseCase
import dmitriy.losev.firebase.core.exception.NullableGroupException
import dmitriy.losev.core.models.Group
import dmitriy.losev.firebase.domain.repositories.groups.FirebaseGroupsRepository
import dmitriy.losev.firebase.domain.usecases.user.FirebaseGetUserUseCase

class FirebaseGetGroupUseCase(
    private val firebaseGroupsRepository: FirebaseGroupsRepository,
    private val firebaseGetUserUseCase: FirebaseGetUserUseCase
): FirebaseBaseUseCase() {

    suspend fun getGroup(groupId: String): Group {
        val user = firebaseGetUserUseCase.getUserWithException()
        return firebaseGroupsRepository.getGroup(teacherId = user.uid, groupId = groupId) ?: throw NullableGroupException()
    }
}