package dmitriy.losev.firebase.data.repositories

import com.google.firebase.database.DatabaseReference
import dmitriy.losev.firebase.data.dto.StudentDTO
import dmitriy.losev.firebase.data.mappers.StudentMapper
import dmitriy.losev.firebase.domain.models.Student
import dmitriy.losev.firebase.domain.models.StudentType
import dmitriy.losev.firebase.domain.repositories.FirebaseStudentsRepository
import kotlinx.coroutines.tasks.await

class FirebaseStudentsRepositoryImpl(
    reference: DatabaseReference,
    private val studentMapper: StudentMapper
): FirebaseStudentsRepository {

    private val students = reference.child("students")

    override suspend fun getStudents(teacherUid: String, studentType: StudentType): List<Student> {
        return students.child(teacherUid).get().await().children.mapNotNull{ dataSnapshot ->
            dataSnapshot.getValue(StudentDTO::class.java)?.let { studentMapper.map(value = it) }
        }
    }
}