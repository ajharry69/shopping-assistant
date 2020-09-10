package com.xently.data.source.local

import androidx.room.TypeConverter
import com.google.gson.reflect.TypeToken
import com.xently.common.utils.JSON_CONVERTER
import com.xently.models.User.AuthProvider
import java.util.*

object RoomTypeConverters {
    class StringListConverter {
        @TypeConverter
        fun stringSetToJsonArray(strs: Set<String>): String = JSON_CONVERTER.toJson(strs)

        @TypeConverter
        fun jsonArrayToStringSet(jsonArray: String): Set<String> =
            JSON_CONVERTER.fromJson(jsonArray, object : TypeToken<Set<String>>() {}.type)
    }

    class ProviderTypeConverter {
        @TypeConverter
        fun fromProviderToString(provider: AuthProvider): String = provider.name

        @TypeConverter
        fun fromStringToProvider(provider: String): AuthProvider =
            AuthProvider.valueOf(provider.toUpperCase(Locale.ROOT))
    }
}