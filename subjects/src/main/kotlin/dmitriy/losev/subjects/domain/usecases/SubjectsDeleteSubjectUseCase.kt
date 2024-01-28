package dmitriy.losev.subjects.domain.usecases

import dmitriy.losev.core.cache.DatabaseLoader
import dmitriy.losev.database.domain.usecases.subjects.DatabaseDeleteSubjectUseCase
import dmitriy.losev.firebase.domain.usecases.subjects.FirebaseDeleteSubjectUseCase
import dmitriy.losev.subjects.core.SubjectsBaseUseCase

class SubjectsDeleteSubjectUseCase(
    private val firebaseDeleteSubjectUseCase: FirebaseDeleteSubjectUseCase,
    private val databaseDeleteSubjectUseCase: DatabaseDeleteSubjectUseCase
): SubjectsBaseUseCase(), DatabaseLoader {

    suspend fun deleteSubject(subjectId: String) {
        deleteData(
            deleteInFirebase = { firebaseDeleteSubjectUseCase.deleteSubject(subjectId) },
            deleteInDatabase = { databaseDeleteSubjectUseCase.deleteSubject(subjectId) }
        )
    }
}