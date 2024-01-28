package dmitriy.losev.subjects.domain.usecases

import dmitriy.losev.core.cache.DatabaseLoader
import dmitriy.losev.core.models.Subject
import dmitriy.losev.database.domain.usecases.subjects.DatabaseGetSubjectUseCase
import dmitriy.losev.database.domain.usecases.subjects.DatabaseSaveSubjectUseCase
import dmitriy.losev.firebase.domain.usecases.subjects.FirebaseGetSubjectUseCase
import dmitriy.losev.subjects.core.SubjectsBaseUseCase

class SubjectsGetSubjectUseCase(
    private val firebaseGetSubjectUseCase: FirebaseGetSubjectUseCase,
    private val databaseGetSubjectUseCase: DatabaseGetSubjectUseCase,
    private val databaseSaveSubjectUseCase: DatabaseSaveSubjectUseCase
) : SubjectsBaseUseCase(), DatabaseLoader {

    suspend fun getSubject(subjectId: String, onFirebaseLoading: (Subject) -> Unit, onDatabaseLoading: (Subject?) -> Unit): Unit = loadData(
        loadFromFirebase = { firebaseGetSubjectUseCase.getSubject(subjectId) },
        loadFromDatabase = { databaseGetSubjectUseCase.getSubject(subjectId) },
        saveToDatabase = { subject -> databaseSaveSubjectUseCase.saveSubject(subject) },
        onFirebaseLoading = onFirebaseLoading,
        onDatabaseLoading = onDatabaseLoading
    )
}