package dmitriy.losev.database.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "students")
data class StudentEntity(

    @PrimaryKey
    @ColumnInfo(name = "student_id")
    val id: String,

    @ColumnInfo(name = "first_name")
    val firstName: String?,

    @ColumnInfo(name = "last_name")
    val lastName: String?,

    @ColumnInfo(name = "patronymic")
    val patronymic: String?,

    @ColumnInfo(name = "phone_number")
    val phoneNumber: String?,

    @ColumnInfo(name = "email")
    val email: String?,

    @ColumnInfo(name = "skype")
    val skype: String?,

    @ColumnInfo(name = "discord")
    val discord: String?,

    @ColumnInfo(name = "comment")
    val comment: String?,

    @ColumnInfo(name = "student_type")
    val studentType: String
)
