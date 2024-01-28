package dmitriy.losev.students.domain.usecases.student

import dmitriy.losev.core.cache.DatabaseLoader
import dmitriy.losev.core.models.Student
import dmitriy.losev.core.models.types.StudentType
import dmitriy.losev.database.domain.usecases.students.DatabaseGetStudentsUseCase
import dmitriy.losev.database.domain.usecases.students.DatabaseSaveStudentsUseCase
import dmitriy.losev.firebase.domain.usecases.students.FirebaseGetStudentsUseCase
import dmitriy.losev.students.core.StudentsBaseUseCase
import dmitriy.losev.students.domain.converters.ActiveStudentConverter
import dmitriy.losev.students.domain.converters.ArchiveStudentConverter
import dmitriy.losev.students.domain.models.ActiveStudent
import dmitriy.losev.students.domain.models.ArchiveStudent

class GetStudentsUseCase(
    private val activeStudentConverter: ActiveStudentConverter,
    private val archiveStudentConverter: ArchiveStudentConverter,
    private val firebaseGetStudentsUseCase: FirebaseGetStudentsUseCase,
    private val databaseGetStudentsUseCase: DatabaseGetStudentsUseCase,
    private val databaseSaveStudentsUseCase: DatabaseSaveStudentsUseCase
) : StudentsBaseUseCase(), DatabaseLoader {

    suspend fun getStudents(
        onFirebaseLoading: (Pair<List<ActiveStudent>, List<ArchiveStudent>>) -> Unit,
        onDatabaseLoading: (Pair<List<ActiveStudent>, List<ArchiveStudent>>) -> Unit
    ) {
        loadAllData(
            loadFromFirebase = { firebaseGetStudentsUseCase.getStudents() },
            loadFromDatabase = { databaseGetStudentsUseCase.getStudents() },
            saveToDatabase = { students -> databaseSaveStudentsUseCase.saveStudents(students) },
            onFirebaseLoading = { students -> onFirebaseLoading(convertStudent(students)) },
            onDatabaseLoading = { students -> onDatabaseLoading(convertStudent(students)) }
        )
    }

    private fun convertStudent(students: List<Student>): Pair<List<ActiveStudent>, List<ArchiveStudent>> {
        val studentGroups = students.groupBy { student -> student.studentType == StudentType.ARCHIVE }
        val activeStudents = studentGroups[false].orEmpty().map { student -> activeStudentConverter.map(value = student) }
        val archiveStudents = studentGroups[true].orEmpty().map { student -> archiveStudentConverter.map(value = student) }
        return activeStudents to archiveStudents
    }
}