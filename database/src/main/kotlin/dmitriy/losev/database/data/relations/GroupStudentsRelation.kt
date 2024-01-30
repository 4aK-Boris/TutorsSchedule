package dmitriy.losev.database.data.relations

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import dmitriy.losev.database.data.entity.GroupEntity
import dmitriy.losev.database.data.entity.StudentEntity
import dmitriy.losev.database.data.entity.StudentGroupCrossRefEntity

data class GroupStudentsRelation(

    @Embedded val group: GroupEntity,

    @Relation(
        parentColumn = "group_id",
        entityColumn = "student_id",
        associateBy = Junction(StudentGroupCrossRefEntity::class)
    )
    val students: List<StudentEntity>
)