package dmitriy.losev.firebase.domain.repositories.students

import dmitriy.losev.firebase.domain.models.SimpleStudent
import dmitriy.losev.firebase.domain.models.Student

interface FirebaseStudentsRepository {

    suspend fun addStudent(teacherUId: String, student: Student): String?

    suspend fun getStudent(teacherUId: String, studentId: String): Student?

    suspend fun updateStudent(teacherUId: String, studentId: String, student: Student)

    suspend fun deleteStudent(teacherUId: String, studentId: String)

    suspend fun getSimpleStudents(teacherUid: String): List<SimpleStudent>
}