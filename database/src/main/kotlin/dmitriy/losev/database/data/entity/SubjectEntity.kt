package dmitriy.losev.database.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "subjects")
data class SubjectEntity(

    @PrimaryKey
    val id: String,

    val name: String,

    val price: Int?
)
