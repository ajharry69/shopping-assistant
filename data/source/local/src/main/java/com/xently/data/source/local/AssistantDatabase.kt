package com.xently.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.xently.data.source.local.daos.UserDAO
import com.xently.models.Token
import com.xently.models.User

@Database(
    entities = [
        User::class,
        Token::class,
    ],
    version = 1,
    exportSchema = true
)
@TypeConverters(RoomTypeConverters.StringListConverter::class)
abstract class AssistantDatabase : RoomDatabase() {
    abstract val userDAO: UserDAO
}