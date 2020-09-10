package com.xently.data.source.local.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.xently.models.DEFAULT_TOKEN_ID
import com.xently.models.Token
import kotlinx.coroutines.flow.Flow

@Dao
interface TokenDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addToken(token: Token): Long

    @Query("SELECT * FROM auth_tokens WHERE id = :id")
    fun getToken(id: Long = DEFAULT_TOKEN_ID): Flow<Token?>
}