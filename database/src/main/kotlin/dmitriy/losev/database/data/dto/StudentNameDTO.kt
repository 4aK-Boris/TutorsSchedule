package dmitriy.losev.database.data.dto

import androidx.room.ColumnInfo

data class StudentNameDTO(

    @ColumnInfo(name = "student_id")
    val id: String,

    @ColumnInfo(name = "first_name")
    val firstName: String?,

    @ColumnInfo(name = "last_name")
    val lastName: String?,

    @ColumnInfo(name = "patronymic")
    val patronymic: String?
)