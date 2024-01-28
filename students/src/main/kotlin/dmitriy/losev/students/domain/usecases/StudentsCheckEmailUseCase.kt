package dmitriy.losev.students.domain.usecases

import dmitriy.losev.students.core.StudentsBaseUseCase
import dmitriy.losev.students.domain.repository.EmailRepository

class StudentsCheckEmailUseCase(private val emailRepository: EmailRepository) : StudentsBaseUseCase() {

    suspend fun checkEmail(email: String) {
        emailRepository.checkEmail(email)
    }

    suspend fun checkEmailForEmpty(email: String) {
        emailRepository.checkEmailForEmpty(email)
    }
}