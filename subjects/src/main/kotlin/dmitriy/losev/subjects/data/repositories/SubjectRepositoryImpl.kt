package dmitriy.losev.subjects.data.repositories

import dmitriy.losev.core.cache.CacheDataHandler
import dmitriy.losev.core.models.Subject
import dmitriy.losev.subjects.data.dto.SubjectDTO
import dmitriy.losev.subjects.data.mappers.SubjectMapper
import dmitriy.losev.subjects.domain.repositories.SubjectRepository
import kotlinx.serialization.builtins.ListSerializer

class SubjectRepositoryImpl(
    private val subjectMapper: SubjectMapper,
    private val cacheDataHandler: CacheDataHandler
): SubjectRepository {

    private val serializer = ListSerializer(elementSerializer = SubjectDTO.serializer())

    override suspend fun loadSubjectsFromCache(userId: String): List<Subject>? {
        val subjectsDTO = cacheDataHandler.loadData(name = "$SUBJECTS_NAME$userId", deserializer = serializer)
        return subjectsDTO?.map { subjectDTO -> subjectMapper.map(value = subjectDTO) }
    }

    override suspend fun saveSubjectToCache(userId: String, subjects: List<Subject>) {
        val subjectsDTO = subjects.map { subject -> subjectMapper.map(value = subject) }
        cacheDataHandler.saveData(name = "$SUBJECTS_NAME$userId", data = subjectsDTO, serializer = serializer)
    }

    companion object {
        private const val SUBJECTS_NAME = "subjects_"
    }
}