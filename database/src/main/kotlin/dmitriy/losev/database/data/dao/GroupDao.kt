package dmitriy.losev.database.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import dmitriy.losev.database.data.entity.GroupEntity
import dmitriy.losev.database.data.relations.GroupStudentsRelation

@Dao
interface GroupDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addGroup(group: GroupEntity)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateGroup(group: GroupEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveGroup(group: GroupEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveGroups(groups: List<GroupEntity>)

    @Delete
    suspend fun deleteGroup(group: GroupEntity)

    @Delete
    suspend fun deleteGroups(groups: List<GroupEntity>)

    @Query("SELECT * FROM groups WHERE group_id = :groupId")
    suspend fun getGroup(groupId: String): GroupEntity?

    @Query("SELECT * FROM groups")
    suspend fun getGroups(): List<GroupEntity>

    @Transaction
    @Query("SELECT * FROM groups WHERE group_id = :groupId")
    suspend fun getGroupStudents(groupId: String): GroupStudentsRelation?
}