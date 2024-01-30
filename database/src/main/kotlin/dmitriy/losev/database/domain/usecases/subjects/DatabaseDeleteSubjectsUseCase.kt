package dmitriy.losev.database.domain.usecases.subjects

import dmitriy.losev.core.models.Subject
import dmitriy.losev.database.core.DatabaseBaseUseCase
import dmitriy.losev.database.domain.repositories.SubjectsRepository

class DatabaseDeleteSubjectsUseCase(private val subjectsRepository: SubjectsRepository): DatabaseBaseUseCase() {

    suspend fun deleteSubjects(subjects: List<Subject>) {
        subjectsRepository.deleteSubjects(subjects)
    }
}