package dmitriy.losev.database.data.mappers

import dmitriy.losev.database.data.entity.StudentGroupCrossRefEntity

class StudentGroupCrossRefMapper {

    fun map(groupId: String, studentId: String): StudentGroupCrossRefEntity {
        return StudentGroupCrossRefEntity(studentId, groupId)
    }
}