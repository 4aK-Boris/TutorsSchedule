package dmitriy.losev.firebase.data.repositories

import com.google.firebase.database.DatabaseReference
import dmitriy.losev.firebase.data.dto.StudentDTO
import dmitriy.losev.firebase.data.mappers.StudentMapper
import dmitriy.losev.firebase.domain.models.Student
import dmitriy.losev.firebase.domain.repositories.FirebaseStudentRepository
import kotlinx.coroutines.tasks.await

class FirebaseStudentRepositoryImpl(
    reference: DatabaseReference,
    private val studentMapper: StudentMapper
) : FirebaseStudentRepository {

    private val students = reference.child("students")

    override suspend fun addStudent(teacherUId: String, student: Student) {
        students.child(teacherUId).push().apply {
            setValue(studentMapper.map(value = student).copy(id = key)).await()
        }
    }

    override suspend fun getStudent(teacherUId: String, studentId: String): Student? {
        return students.child(teacherUId).child(studentId).get().await().getValue(StudentDTO::class.java)?.let { studentDTO ->
            studentMapper.map(value = studentDTO)
        }
    }

    override suspend fun updateStudent(teacherUId: String, student: Student) {
        val studentDTO = studentMapper.map(value = student)
        studentDTO.id?.let { id -> students.child(teacherUId).child(id).setValue(studentDTO).await() } ?: addStudent(teacherUId, student)
    }

    suspend fun deleteStudent(teacherUId: String, student: Student) {
        //students.child(teacherUId).get().await().
    }
}