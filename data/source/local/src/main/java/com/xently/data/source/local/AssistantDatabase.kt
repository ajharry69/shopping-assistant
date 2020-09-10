package com.xently.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.xently.data.source.local.RoomTypeConverters.ProviderTypeConverter
import com.xently.data.source.local.RoomTypeConverters.StringListConverter
import com.xently.data.source.local.daos.TokenDAO
import com.xently.data.source.local.daos.UserDAO
import com.xently.models.Token
import com.xently.models.User

@Database(
    entities = [
        Token::class,
        User::class,
    ],
    version = BuildConfig.DATABASE_VERSION,
    exportSchema = true
)
@TypeConverters(StringListConverter::class, ProviderTypeConverter::class)
abstract class AssistantDatabase : RoomDatabase() {
    abstract val userDAO: UserDAO
    abstract val tokenDAO: TokenDAO
}