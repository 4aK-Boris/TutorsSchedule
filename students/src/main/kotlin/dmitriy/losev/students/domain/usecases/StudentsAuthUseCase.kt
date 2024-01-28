package dmitriy.losev.students.domain.usecases

import dmitriy.losev.firebase.domain.usecases.auth.FirebaseAuthUseCase
import dmitriy.losev.students.core.StudentsBaseUseCase

class StudentsAuthUseCase(private val firebaseAuthUseCase: FirebaseAuthUseCase) : StudentsBaseUseCase() {

    suspend fun isAuth(): Boolean{
        return firebaseAuthUseCase.isAuth()
    }
}