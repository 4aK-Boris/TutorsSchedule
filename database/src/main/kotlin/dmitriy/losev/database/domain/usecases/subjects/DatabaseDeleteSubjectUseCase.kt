package dmitriy.losev.database.domain.usecases.subjects

import dmitriy.losev.database.core.DatabaseBaseUseCase
import dmitriy.losev.database.domain.repositories.SubjectsRepository

class DatabaseDeleteSubjectUseCase(private val subjectsRepository: SubjectsRepository): DatabaseBaseUseCase() {

    suspend fun deleteSubject(subjectId: String) {
        subjectsRepository.deleteSubject(subjectId)
    }
}