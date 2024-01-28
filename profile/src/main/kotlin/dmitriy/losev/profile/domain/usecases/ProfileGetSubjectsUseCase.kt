package dmitriy.losev.profile.domain.usecases

import dmitriy.losev.core.cache.DatabaseLoader
import dmitriy.losev.core.models.Subject
import dmitriy.losev.database.domain.usecases.subjects.DatabaseGetSubjectsUseCase
import dmitriy.losev.database.domain.usecases.subjects.DatabaseSaveSubjectsUseCase
import dmitriy.losev.firebase.domain.usecases.subjects.FirebaseGetSubjectsUseCase
import dmitriy.losev.profile.core.ProfileBaseUseCase

class ProfileGetSubjectsUseCase(
    private val firebaseGetSubjectsUseCase: FirebaseGetSubjectsUseCase,
    private val databaseGetSubjectsUseCase: DatabaseGetSubjectsUseCase,
    private val databaseSaveSubjectsUseCase: DatabaseSaveSubjectsUseCase
): ProfileBaseUseCase(), DatabaseLoader {

    suspend fun getSubjects(onLoading: (List<Subject>) -> Unit) {
        loadAllData(
            loadFromFirebase = firebaseGetSubjectsUseCase::getSubjects,
            loadFromDatabase = databaseGetSubjectsUseCase::getSubjects,
            saveToDatabase = databaseSaveSubjectsUseCase::saveSubjects,
            onFirebaseLoading = onLoading,
            onDatabaseLoading = onLoading
        )
    }
}