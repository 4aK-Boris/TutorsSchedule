package dmitriy.losev.database.domain.repositories

import dmitriy.losev.core.models.Student

interface StudentsRepository {

    suspend fun addStudent(student: Student)

    suspend fun updateStudent(student: Student)

    suspend fun saveStudent(student: Student)

    suspend fun saveStudents(students: List<Student>)

    suspend fun deleteStudent(studentId: String)

    suspend fun getStudent(studentId: String): Student?

    suspend fun getStudents(): List<Student>
}