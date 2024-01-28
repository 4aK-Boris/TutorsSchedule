package dmitriy.losev.students.domain.usecases.student

import dmitriy.losev.core.cache.DatabaseLoader
import dmitriy.losev.core.models.Student
import dmitriy.losev.core.models.types.StudentType
import dmitriy.losev.core.trimAndFirstCharToUpperCase
import dmitriy.losev.database.domain.usecases.students.DatabaseAddStudentUseCase
import dmitriy.losev.firebase.domain.usecases.students.FirebaseAddStudentUseCase
import dmitriy.losev.students.core.EMPTY_STRING
import dmitriy.losev.students.core.StudentsBaseUseCase

class AddStudentUseCase(
    private val firebaseAddStudentUseCase: FirebaseAddStudentUseCase,
    private val databaseAddStudentUseCase: DatabaseAddStudentUseCase,
    private val checkStudentUseCase: CheckStudentUseCase
) : StudentsBaseUseCase(), DatabaseLoader {

    suspend fun addStudent(
        firstName: String,
        lastName: String,
        patronymic: String,
        phoneNumber: String,
        email: String,
        skype: String,
        discord: String,
        comment: String
    ) {
        addData(
            data = createAndCheckStudent(firstName, lastName, patronymic, phoneNumber, email, skype, discord, comment),
            addToFirebase = { student -> firebaseAddStudentUseCase.addStudent(student) },
            addToDatabase = { student -> databaseAddStudentUseCase.addStudent(student) }
        )
    }

    private suspend fun createAndCheckStudent(
        firstName: String,
        lastName: String,
        patronymic: String,
        phoneNumber: String,
        email: String,
        skype: String,
        discord: String,
        comment: String
    ): Student {
        val student =  Student(
            id = EMPTY_STRING,
            firstName = firstName.trimAndFirstCharToUpperCase(),
            lastName = lastName.trimAndFirstCharToUpperCase(),
            patronymic = patronymic.trimAndFirstCharToUpperCase(),
            name = EMPTY_STRING,
            shortName = EMPTY_STRING,
            phoneNumber = phoneNumber.trim(),
            email = email.trim(),
            skype = skype.trim(),
            discord = discord.trim(),
            comment = comment.trimAndFirstCharToUpperCase(),
            studentType = StudentType.NEW
        )
        checkStudentUseCase.checkStudent(student)
        return student
    }
}