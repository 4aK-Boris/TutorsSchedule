package dmitriy.losev.database.data.repositories.groups

import dmitriy.losev.core.models.Group
import dmitriy.losev.database.data.dao.GroupDao
import dmitriy.losev.database.data.mappers.GroupMapper
import dmitriy.losev.database.domain.repositories.groups.GroupsRepository

class GroupsRepositoryImpl(
    private val groupDao: GroupDao,
    private val groupMapper: GroupMapper
) : GroupsRepository {

    override suspend fun addGroup(group: Group) {
        val groupEntity = groupMapper.map(value = group)
        groupDao.addGroup(groupEntity)
    }

    override suspend fun updateGroup(group: Group) {
        val groupEntity = groupMapper.map(value = group)
        groupDao.updateGroup(groupEntity)
    }

    override suspend fun saveGroup(group: Group) {
        val groupEntity = groupMapper.map(value = group)
        groupDao.saveGroup(groupEntity)
    }

    override suspend fun saveGroups(groups: List<Group>) {
        val groupEntities = groups.map { group -> groupMapper.map(value = group) }
        groupDao.saveGroups(groupEntities)
    }

    override suspend fun deleteGroup(groupId: String) {
        groupDao.getGroup(groupId)?.let { groupEntity ->
            groupDao.deleteGroup(groupEntity)
        }
    }

    override suspend fun deleteGroups(groups: List<Group>) {
        val groupEntities = groups.map { group -> groupMapper.map(value = group) }
        groupDao.deleteGroups(groupEntities)
    }

    override suspend fun getGroup(groupId: String): Group? {
        return groupMapper.map(value = groupDao.getGroup(groupId))
    }

    override suspend fun getGroups(): List<Group> {
        return groupDao.getGroups().mapNotNull { group -> groupMapper.map(value = group) }
    }
}