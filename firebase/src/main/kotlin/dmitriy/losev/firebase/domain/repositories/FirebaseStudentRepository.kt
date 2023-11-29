package dmitriy.losev.firebase.domain.repositories

import dmitriy.losev.firebase.domain.models.Student

interface FirebaseStudentRepository {

    suspend fun addStudent(teacherUId: String, student: Student)

    suspend fun getStudent(teacherUId: String, studentId: String): Student?

    suspend fun updateStudent(teacherUId: String, student: Student)
}