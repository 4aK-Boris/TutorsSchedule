package dmitriy.losev.firebase.data.repositories.students

import com.google.firebase.database.DatabaseReference
import dmitriy.losev.firebase.core.STUDENTS
import dmitriy.losev.firebase.data.dto.SimpleStudentDTO
import dmitriy.losev.firebase.data.dto.StudentDTO
import dmitriy.losev.firebase.data.mappers.SimpleStudentMapper
import dmitriy.losev.firebase.data.mappers.StudentMapper
import dmitriy.losev.firebase.domain.models.SimpleStudent
import dmitriy.losev.firebase.domain.models.Student
import dmitriy.losev.firebase.domain.repositories.students.FirebaseStudentsRepository
import kotlinx.coroutines.tasks.await

class FirebaseStudentsRepositoryImpl(
    reference: DatabaseReference,
    private val studentMapper: StudentMapper,
    private val simpleStudentMapper: SimpleStudentMapper
) : FirebaseStudentsRepository {

    private val students by lazy { reference.child(STUDENTS) }

    override suspend fun addStudent(teacherUId: String, student: Student): String? {
        students.child(teacherUId).push().apply {
            setValue(studentMapper.map(value = student).copy(id = key)).await()
            return key
        }
    }

    override suspend fun getStudent(teacherUId: String, studentId: String): Student? {
        return students.child(teacherUId).child(studentId).get().await().getValue(StudentDTO::class.java)?.let { studentDTO ->
            studentMapper.map(value = studentDTO)
        }
    }

    override suspend fun updateStudent(teacherUId: String, studentId: String, student: Student) {
        students.child(teacherUId).updateChildren(mapOf(studentId to studentMapper.map(value = student))).await()
    }

    override suspend fun deleteStudent(teacherUId: String, studentId: String) {
        students.child(teacherUId).child(studentId).removeValue().await()
    }

    override suspend fun getSimpleStudents(teacherUid: String): List<SimpleStudent> {
        return students.child(teacherUid).get().await().children.mapNotNull { dataSnapshot ->
            dataSnapshot.getValue(SimpleStudentDTO::class.java)?.let { simpleStudentDTO ->
                simpleStudentMapper.map(value = simpleStudentDTO)
            }
        }
    }
}