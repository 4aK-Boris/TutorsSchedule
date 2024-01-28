package dmitriy.losev.subjects.domain.usecases

import dmitriy.losev.core.cache.DatabaseLoader
import dmitriy.losev.core.models.Subject
import dmitriy.losev.database.domain.usecases.subjects.DatabaseAddSubjectUseCase
import dmitriy.losev.firebase.domain.usecases.subjects.FirebaseAddSubjectUseCase
import dmitriy.losev.subjects.core.EMPTY_STRING
import dmitriy.losev.subjects.core.SubjectsBaseUseCase

class SubjectsAddSubjectUseCase(
    private val firebaseAddSubjectUseCase: FirebaseAddSubjectUseCase,
    private val databaseAddSubjectUseCase: DatabaseAddSubjectUseCase,
    private val subjectsCheckNameUseCase: SubjectsCheckNameUseCase
): SubjectsBaseUseCase(), DatabaseLoader {

    suspend fun addSubject(name: String, price: String) {
        subjectsCheckNameUseCase.checkSubjectName(name)
        val subjectModel = Subject(id = EMPTY_STRING, name = name, price = price)
        addData(
            data = subjectModel,
            addToFirebase = { subject -> firebaseAddSubjectUseCase.addSubject(subject) },
            addToDatabase = { subject -> databaseAddSubjectUseCase.addSubject(subject) }
        )
    }
}