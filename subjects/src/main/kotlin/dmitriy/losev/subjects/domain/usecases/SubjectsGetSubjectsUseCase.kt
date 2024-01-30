package dmitriy.losev.subjects.domain.usecases

import dmitriy.losev.core.cache.DatabaseLoader
import dmitriy.losev.core.models.Subject
import dmitriy.losev.database.domain.usecases.subjects.DatabaseDeleteSubjectsUseCase
import dmitriy.losev.database.domain.usecases.subjects.DatabaseGetSubjectsUseCase
import dmitriy.losev.database.domain.usecases.subjects.DatabaseSaveSubjectsUseCase
import dmitriy.losev.firebase.domain.usecases.subjects.FirebaseGetSubjectsUseCase
import dmitriy.losev.subjects.core.SubjectsBaseUseCase

class SubjectsGetSubjectsUseCase(
    private val firebaseGetSubjectsUseCase: FirebaseGetSubjectsUseCase,
    private val databaseGetSubjectsUseCase: DatabaseGetSubjectsUseCase,
    private val databaseSaveSubjectsUseCase: DatabaseSaveSubjectsUseCase,
    private val databaseDeleteSubjectsUseCase: DatabaseDeleteSubjectsUseCase
): SubjectsBaseUseCase(), DatabaseLoader {

    suspend fun getSubjects(onFirebaseLoading: (List<Subject>) -> Unit, onDatabaseLoading: (List<Subject>) -> Unit) {
        return loadAllData(
            loadFromFirebase = firebaseGetSubjectsUseCase::getSubjects,
            loadFromDatabase = databaseGetSubjectsUseCase::getSubjects,
            saveToDatabase = databaseSaveSubjectsUseCase::saveSubjects,
            deleteFromDatabase = databaseDeleteSubjectsUseCase::deleteSubjects,
            onFirebaseLoading = onFirebaseLoading,
            onDatabaseLoading = onDatabaseLoading
        )
    }
}