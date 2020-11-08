package com.xently.models.user

import androidx.room.Embedded
import androidx.room.Relation
import com.xently.models.Token

data class UserWithToken(
    @Embedded val u: User,
    @Relation(parentColumn = "id", entityColumn = "id")
    val token: Token,
) {
    val user: User
        get() = u.copy(token = token)
}