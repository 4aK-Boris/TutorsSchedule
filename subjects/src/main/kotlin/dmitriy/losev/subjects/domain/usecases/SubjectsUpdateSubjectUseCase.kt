package dmitriy.losev.subjects.domain.usecases

import dmitriy.losev.core.cache.DatabaseLoader
import dmitriy.losev.core.models.Subject
import dmitriy.losev.database.domain.usecases.subjects.DatabaseUpdateSubjectUseCase
import dmitriy.losev.firebase.domain.usecases.subjects.FirebaseUpdateSubjectUseCase
import dmitriy.losev.subjects.core.SubjectsBaseUseCase

class SubjectsUpdateSubjectUseCase(
    private val firebaseUpdateSubjectUseCase: FirebaseUpdateSubjectUseCase,
    private val databaseUpdateSubjectUseCase: DatabaseUpdateSubjectUseCase,
    private val subjectsCheckNameUseCase: SubjectsCheckNameUseCase
) : SubjectsBaseUseCase(), DatabaseLoader {

    suspend fun updateSubject(subjectId: String, subject: Subject) {
        subjectsCheckNameUseCase.checkSubjectName(subject.name)
        updateData(
            data = subject,
            updateInFirebase = { firebaseUpdateSubjectUseCase.updateSubject(subjectId, it) },
            updateInDatabase = { databaseUpdateSubjectUseCase.updateSubject(it) }
        )
    }
}