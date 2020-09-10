package com.xently.models

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.xently.common.utils.Exclude
import com.xently.common.utils.web.AuthScheme

const val DEFAULT_TOKEN_ID = DEFAULT_USER_ID

@Entity(tableName = "auth_tokens")
data class Token(
    var access: String = "",
    var refresh: String = access,
    @Exclude
    @PrimaryKey(autoGenerate = false)
    var id: Long = DEFAULT_TOKEN_ID,
    @Exclude
    @Ignore
    val scheme: AuthScheme = AuthScheme.BEARER,
) {
    override fun toString(): String = "$scheme $access"
}