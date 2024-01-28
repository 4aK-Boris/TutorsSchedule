package dmitriy.losev.database.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "contacts")
data class ContactEntity(

    @PrimaryKey
    val id: String,

    @ColumnInfo(name = "student_id")
    val studentId: String,

    @ColumnInfo(name = "first_name")
    val firstName: String?,

    @ColumnInfo(name = "last_name")
    val lastName: String?,

    val patronymic: String?,

    @ColumnInfo(name = "phone_number")
    val phoneNumber: String?
)
