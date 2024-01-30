package dmitriy.losev.database.domain.repositories.students

import dmitriy.losev.core.models.Student
import dmitriy.losev.core.models.StudentName

interface StudentsRepository {

    suspend fun addStudent(student: Student)

    suspend fun updateStudent(student: Student)

    suspend fun saveStudent(student: Student)

    suspend fun saveStudents(students: List<Student>)

    suspend fun deleteStudent(studentId: String)

    suspend fun deleteStudents(students: List<Student>)

    suspend fun getStudent(studentId: String): Student?

    suspend fun getStudents(): List<Student>

    suspend fun getStudentNames(): List<StudentName>

    suspend fun getStudentNames(studentIds: List<String>): List<StudentName>
}