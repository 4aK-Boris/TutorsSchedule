package dmitriy.losev.firebase.domain.usecases.auth

import com.google.firebase.database.FirebaseDatabase
import dmitriy.losev.firebase.core.FirebaseBaseUseCase
import dmitriy.losev.firebase.domain.usecases.user.FirebaseGetUserIdUseCase

class FirebaseConfiguringDatabaseUseCase(
    private val database: FirebaseDatabase,
    private val firebaseGetUserIdUseCase: FirebaseGetUserIdUseCase
): FirebaseBaseUseCase() {

    suspend fun configureDatabase() {
        database.setPersistenceEnabled(true)
        val userId = firebaseGetUserIdUseCase.getUserIdWithException()
        database.getReference(userId).keepSynced(true)
    }
}