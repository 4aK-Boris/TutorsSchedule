package dmitriy.losev.firebase.domain.repositories.students

import dmitriy.losev.core.models.Student
import dmitriy.losev.core.models.StudentName

interface FirebaseStudentsRepository {

    suspend fun addStudent(teacherId: String, student: Student): String?

    suspend fun getStudent(teacherId: String, studentId: String): Student?

    suspend fun updateStudent(teacherId: String, studentId: String, student: Student)

    suspend fun deleteStudent(teacherId: String, studentId: String)

    suspend fun getStudents(teacherId: String): List<Student>

    suspend fun getStudentNames(teacherId: String): List<StudentName>
}