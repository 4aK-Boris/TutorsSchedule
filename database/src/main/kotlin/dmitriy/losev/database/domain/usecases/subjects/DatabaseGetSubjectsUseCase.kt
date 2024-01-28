package dmitriy.losev.database.domain.usecases.subjects

import dmitriy.losev.core.models.Subject
import dmitriy.losev.database.core.DatabaseBaseUseCase
import dmitriy.losev.database.domain.repositories.SubjectsRepository

class DatabaseGetSubjectsUseCase(private val subjectsRepository: SubjectsRepository): DatabaseBaseUseCase() {

    suspend fun getSubjects(): List<Subject> {
        return subjectsRepository.getSubjects()
    }
}