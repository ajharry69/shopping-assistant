package com.xently.data.source.local.daos

import androidx.room.*
import com.xently.models.user.DEFAULT_USER_ID
import com.xently.models.user.User
import com.xently.models.user.UserWithToken
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addUser(user: User): Long

    @Query("SELECT * FROM user WHERE id = :id")
    fun getUser(id: Long = DEFAULT_USER_ID): Flow<User?>

    @Transaction
    @Query("SELECT * FROM user WHERE id = :id")
    fun getUserWithToken(id: Long = DEFAULT_USER_ID): Flow<UserWithToken?>

    @Query("DELETE FROM user")
    suspend fun removeUser()
}