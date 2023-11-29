package dmitriy.losev.firebase.domain.repositories

import dmitriy.losev.firebase.domain.models.Student
import dmitriy.losev.firebase.domain.models.StudentType

interface FirebaseStudentsRepository {

    suspend fun getStudents(teacherUid: String, studentType: StudentType): List<Student>
}