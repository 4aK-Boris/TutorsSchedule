package dmitriy.losev.database.data.relations

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import dmitriy.losev.database.data.entity.GroupEntity
import dmitriy.losev.database.data.entity.StudentEntity
import dmitriy.losev.database.data.entity.StudentGroupCrossRefEntity

data class StudentGroupsRelation(

    @Embedded val student: StudentEntity,

    @Relation(
        parentColumn = "student_id",
        entityColumn = "group_id",
        associateBy = Junction(StudentGroupCrossRefEntity::class)
    )
    val groups: List<GroupEntity>
)