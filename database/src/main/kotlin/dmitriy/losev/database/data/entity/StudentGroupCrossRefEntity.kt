package dmitriy.losev.database.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(
    tableName = "student_group_cross_ref",
    primaryKeys = ["student_id", "group_id"],
    foreignKeys = [
        ForeignKey(entity = StudentEntity::class, parentColumns = ["student_id"], childColumns = ["student_id"], onDelete = ForeignKey.CASCADE),
        ForeignKey(entity = GroupEntity::class, parentColumns = ["group_id"], childColumns = ["group_id"], onDelete = ForeignKey.CASCADE)
    ]
)
data class StudentGroupCrossRefEntity(

    @ColumnInfo(name = "student_id")
    val studentId: String,

    @ColumnInfo(name = "group_id")
    val groupId: String
)