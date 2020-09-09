package com.xently.data.source.local.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.xently.models.User
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDAO {
    @Insert
    suspend fun addUser(user: User): Long

    @Query("SELECT * FROM user WHERE id = :id")
    fun getUser(id: Long): Flow<User>
}