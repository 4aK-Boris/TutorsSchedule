package dmitriy.losev.database.data.repositories

import dmitriy.losev.core.models.Subject
import dmitriy.losev.database.data.dao.SubjectDao
import dmitriy.losev.database.data.mappers.SubjectMapper
import dmitriy.losev.database.domain.repositories.SubjectsRepository

class SubjectsRepositoryImpl(
    private val subjectDao: SubjectDao,
    private val subjectMapper: SubjectMapper
) : SubjectsRepository {

    override suspend fun addSubject(subject: Subject) {
        val subjectEntity = subjectMapper.map(value = subject)
        subjectDao.addSubject(subjectEntity)
    }

    override suspend fun updateSubject(subject: Subject) {
        val subjectEntity = subjectMapper.map(value = subject)
        subjectDao.updateSubject(subjectEntity)
    }

    override suspend fun saveSubject(subject: Subject) {
        val subjectEntity = subjectMapper.map(value = subject)
        subjectDao.saveSubject(subjectEntity)
    }

    override suspend fun saveSubjects(subjects: List<Subject>) {
        val subjectEntities = subjects.map { subject -> subjectMapper.map(value = subject) }
        subjectDao.saveSubjects(subjectEntities)
    }

    override suspend fun deleteSubject(subjectId: String) {
        subjectDao.getSubject(subjectId)?.let { subjectEntity ->
            subjectDao.deleteSubject(subjectEntity)
        }
    }

    override suspend fun deleteSubjects(subjects: List<Subject>) {
        val subjectEntities = subjects.map { subject -> subjectMapper.map(value = subject) }
        subjectDao.deleteSubjects(subjectEntities)
    }

    override suspend fun getSubject(subjectId: String): Subject? {
        return subjectMapper.map(value = subjectDao.getSubject(subjectId))
    }

    override suspend fun getSubjects(): List<Subject> {
        return subjectDao.getSubjects().mapNotNull { subject -> subjectMapper.map(value = subject) }
    }
}