package com.xently.data.source.local.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.xently.models.user.DEFAULT_USER_ID
import com.xently.models.user.User
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addUser(user: User): Long

    @Query("SELECT * FROM user WHERE id = :id")
    fun getUser(id: Long = DEFAULT_USER_ID): Flow<User?>

    @Query("DELETE FROM user")
    suspend fun removeUser()
}