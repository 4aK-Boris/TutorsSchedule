package dmitriy.losev.database.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "contacts",
    foreignKeys = [ForeignKey(
        entity = StudentEntity::class,
        parentColumns = ["student_id"],
        childColumns = ["student_id"],
        onDelete = ForeignKey.CASCADE,
        onUpdate = ForeignKey.NO_ACTION
    )]
)

data class ContactEntity(

    @PrimaryKey
    @ColumnInfo(name = "contact_id")
    val id: String,

    @ColumnInfo(name = "student_id")
    val studentId: String,

    @ColumnInfo(name = "first_name")
    val firstName: String?,

    @ColumnInfo(name = "last_name")
    val lastName: String?,

    @ColumnInfo(name = "patronymic")
    val patronymic: String?,

    @ColumnInfo(name = "phone_number")
    val phoneNumber: String?
)
