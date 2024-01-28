package dmitriy.losev.firebase.data.repositories.students

import com.google.firebase.database.DatabaseReference
import dmitriy.losev.core.models.Student
import dmitriy.losev.firebase.core.STUDENTS
import dmitriy.losev.firebase.data.dto.StudentDTO
import dmitriy.losev.firebase.data.mappers.StudentMapper
import dmitriy.losev.firebase.domain.repositories.students.FirebaseStudentsRepository
import kotlinx.coroutines.tasks.await

class FirebaseStudentsRepositoryImpl(
    private val reference: DatabaseReference,
    private val studentMapper: StudentMapper
) : FirebaseStudentsRepository {

    override suspend fun addStudent(teacherId: String, student: Student): String? {
        getStudentsReference(teacherId).push().apply {
            setValue(studentMapper.map(value = student).copy(id = key)).await()
            return key
        }
    }

    override suspend fun getStudent(teacherId: String, studentId: String): Student? {
        return getStudentsReference(teacherId).child(studentId).get().await().getValue(StudentDTO::class.java)?.let { studentDTO ->
            studentMapper.map(value = studentDTO)
        }
    }

    override suspend fun updateStudent(teacherId: String, studentId: String, student: Student) {
        getStudentsReference(teacherId).updateChildren(mapOf(studentId to studentMapper.map(value = student))).await()
    }

    override suspend fun deleteStudent(teacherId: String, studentId: String) {
        getStudentsReference(teacherId).child(studentId).removeValue().await()
    }

    override suspend fun getStudents(teacherId: String): List<Student> {
        return getStudentsReference(teacherId).get().await().children.mapNotNull { dataSnapshot ->
            dataSnapshot.getValue(StudentDTO::class.java)?.let { studentDTO ->
                studentMapper.map(value = studentDTO)
            }
        }
    }

    private fun getStudentsReference(teacherId: String): DatabaseReference {
        return reference.child(teacherId).child(STUDENTS)
    }
}