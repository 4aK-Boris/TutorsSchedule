package dmitriy.losev.database.domain.usecases.subjects

import dmitriy.losev.core.models.Subject
import dmitriy.losev.database.core.DatabaseBaseUseCase
import dmitriy.losev.database.domain.repositories.SubjectsRepository

class DatabaseSaveSubjectUseCase(private val subjectsRepository: SubjectsRepository): DatabaseBaseUseCase() {

    suspend fun saveSubject(subject: Subject) {
        subjectsRepository.saveSubject(subject)
    }
}