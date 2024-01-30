package dmitriy.losev.database.data.repositories.students

import dmitriy.losev.core.models.Student
import dmitriy.losev.core.models.StudentName
import dmitriy.losev.database.data.dao.StudentDao
import dmitriy.losev.database.data.mappers.StudentMapper
import dmitriy.losev.database.data.mappers.StudentNameMapper
import dmitriy.losev.database.domain.repositories.students.StudentsRepository

class StudentsRepositoryImpl(
    private val studentDao: StudentDao,
    private val studentMapper: StudentMapper,
    private val studentNameMapper: StudentNameMapper
) : StudentsRepository {

    override suspend fun addStudent(student: Student) {
        val studentEntity = studentMapper.map(value = student)
        studentDao.addStudent(studentEntity)
    }

    override suspend fun updateStudent(student: Student) {
        val studentEntity = studentMapper.map(value = student)
        studentDao.updateStudent(studentEntity)
    }

    override suspend fun saveStudent(student: Student) {
        val studentEntity = studentMapper.map(value = student)
        studentDao.saveStudent(studentEntity)
    }

    override suspend fun saveStudents(students: List<Student>) {
        val studentEntities = students.map { student -> studentMapper.map(value = student) }
        studentDao.saveStudents(studentEntities)
    }

    override suspend fun deleteStudent(studentId: String) {
        studentDao.getStudent(studentId)?.let { studentEntity ->
            studentDao.deleteStudent(studentEntity)
        }
    }

    override suspend fun deleteStudents(students: List<Student>) {
        val studentEntities = students.map { student -> studentMapper.map(value = student) }
        studentDao.deleteStudents(studentEntities)
    }

    override suspend fun getStudent(studentId: String): Student? {
        return studentMapper.map(value = studentDao.getStudent(studentId))
    }

    override suspend fun getStudents(): List<Student> {
        return studentDao.getStudents().mapNotNull { student -> studentMapper.map(value = student) }
    }

    override suspend fun getStudentNames(): List<StudentName> {
        return studentDao.getStudentNames().map { studentNameDTO -> studentNameMapper.map(value = studentNameDTO) }
    }

    override suspend fun getStudentNames(studentIds: List<String>): List<StudentName> {
        return studentDao.getStudentNames(studentIds).map { studentNameDTO -> studentNameMapper.map(value = studentNameDTO) }
    }
}