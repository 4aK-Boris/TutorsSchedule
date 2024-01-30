package dmitriy.losev.students.data.repository

import dmitriy.losev.students.core.exception.EmptyFirstNameException
import dmitriy.losev.students.core.exception.EmptyNameException
import dmitriy.losev.students.domain.repository.NameRepository

class NameRepositoryImpl: NameRepository {

    override suspend fun checkFirstName(firstName: String) {
        if (firstName.isBlank()) throw EmptyFirstNameException()
    }

    override suspend fun checkName(name: String) {
        if (name.isBlank()) throw EmptyNameException()
    }
}