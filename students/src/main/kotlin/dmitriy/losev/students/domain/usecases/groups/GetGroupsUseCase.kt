package dmitriy.losev.students.domain.usecases.groups

import dmitriy.losev.core.cache.DatabaseLoader
import dmitriy.losev.core.models.Group
import dmitriy.losev.core.models.types.GroupType
import dmitriy.losev.database.domain.usecases.groups.DatabaseDeleteGroupsUseCase
import dmitriy.losev.database.domain.usecases.groups.DatabaseGetGroupsUseCase
import dmitriy.losev.database.domain.usecases.groups.DatabaseSaveGroupsUseCase
import dmitriy.losev.database.domain.usecases.students.groups.DatabaseGetStudentGroupsUseCase
import dmitriy.losev.firebase.domain.usecases.groups.FirebaseGetGroupsUseCase
import dmitriy.losev.firebase.domain.usecases.students.groups.FirebaseGetStudentGroupsUseCase
import dmitriy.losev.students.core.StudentsBaseUseCase

class GetGroupsUseCase(
    private val firebaseGetGroupsUseCase: FirebaseGetGroupsUseCase,
    private val firebaseGetStudentGroupsUseCase: FirebaseGetStudentGroupsUseCase,
    private val databaseGetGroupsUseCase: DatabaseGetGroupsUseCase,
    private val databaseGetStudentGroupsUseCase: DatabaseGetStudentGroupsUseCase,
    private val databaseSaveGroupsUseCase: DatabaseSaveGroupsUseCase,
    private val databaseDeleteGroupsUseCase: DatabaseDeleteGroupsUseCase
): StudentsBaseUseCase(), DatabaseLoader {

    private suspend fun getGroups(
        onFirebaseLoading: (Pair<List<Group>, List<Group>>) -> Unit,
        onDatabaseLoading: (Pair<List<Group>, List<Group>>) -> Unit
    ) {
        loadAllData(
            loadFromFirebase = firebaseGetGroupsUseCase::getGroups,
            loadFromDatabase = databaseGetGroupsUseCase::getGroups,
            saveToDatabase = databaseSaveGroupsUseCase::saveGroups,
            deleteFromDatabase = databaseDeleteGroupsUseCase::deleteGroups,
            onFirebaseLoading = { groups -> onFirebaseLoading(convertGroups(groups)) },
            onDatabaseLoading = { groups -> onDatabaseLoading(convertGroups(groups)) }
        )
    }

    suspend fun getGroups(
        studentId: String?,
        onFirebaseLoading: (Pair<List<Group>, List<Group>>) -> Unit,
        onDatabaseLoading: (Pair<List<Group>, List<Group>>) -> Unit
    ) {
        if (studentId == null) {
            getGroups(onFirebaseLoading, onDatabaseLoading)
        } else {
            loadAllData(
                loadFromFirebase = { firebaseGetStudentGroupsUseCase.getStudentGroups(studentId) },
                loadFromDatabase = { databaseGetStudentGroupsUseCase.getStudentGroups(studentId) },
                saveToDatabase = databaseSaveGroupsUseCase::saveGroups,
                deleteFromDatabase = databaseDeleteGroupsUseCase::deleteGroups,
                onFirebaseLoading = { groups -> onFirebaseLoading(convertGroups(groups)) },
                onDatabaseLoading = { groups -> onDatabaseLoading(convertGroups(groups)) }
            )
        }
    }

    private fun convertGroups(groups: List<Group>): Pair<List<Group>, List<Group>> {
        val groupsPair = groups.groupBy { group -> group.groupType == GroupType.ARCHIVE }
        val activeGroups = groupsPair[false].orEmpty()
        val archiveGroups = groupsPair[true].orEmpty()
        return activeGroups to archiveGroups
    }
}