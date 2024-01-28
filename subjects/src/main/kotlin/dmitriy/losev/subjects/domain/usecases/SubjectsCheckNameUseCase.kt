package dmitriy.losev.subjects.domain.usecases

import dmitriy.losev.subjects.core.SubjectsBaseUseCase
import dmitriy.losev.subjects.domain.repositories.SubjectsNameRepository

class SubjectsCheckNameUseCase(private val subjectsNameRepository: SubjectsNameRepository): SubjectsBaseUseCase() {

    suspend fun checkSubjectName(name: String) {
        subjectsNameRepository.checkSubjectName(name)
    }
}