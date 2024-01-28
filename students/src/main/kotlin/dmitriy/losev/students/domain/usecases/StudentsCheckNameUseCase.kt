package dmitriy.losev.students.domain.usecases

import dmitriy.losev.students.core.StudentsBaseUseCase
import dmitriy.losev.students.domain.repository.NameRepository

class StudentsCheckNameUseCase(private val nameRepository: NameRepository): StudentsBaseUseCase() {

    suspend fun checkFirstName(firstName: String) {
        nameRepository.checkFirstName(firstName)
    }
}