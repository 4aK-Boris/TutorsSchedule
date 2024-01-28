package dmitriy.losev.subjects.data.repositories

import dmitriy.losev.subjects.core.exception.NameEmptyException
import dmitriy.losev.subjects.domain.repositories.SubjectsNameRepository

class SubjectsNameRepositoryImpl : SubjectsNameRepository {

    override suspend fun checkSubjectName(name: String) {
        if (name.isBlank()) throw NameEmptyException()
    }
}