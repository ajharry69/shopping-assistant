package com.xently.models

import android.net.Uri
import androidx.room.*
import com.xently.common.utils.Exclude
import java.io.File

const val DEFAULT_USER_ID = 1L

@Entity
data class User(
    var username: String = "",
    var email: String? = null,
    var firstName: String? = null,
    var lastName: String? = null,
    var provider: AuthProvider = AuthProvider.EMAIL,
    @Exclude(Exclude.During.SERIALIZATION)
    var photoUrl: String? = null,
    @Exclude(Exclude.During.SERIALIZATION)
    var isVerified: Boolean = provider != AuthProvider.EMAIL,
    @Ignore
    @Exclude(Exclude.During.SERIALIZATION)
    val token: Token = Token(),
    @Exclude(Exclude.During.SERIALIZATION)
    @PrimaryKey(autoGenerate = false)
    var id: Long = DEFAULT_USER_ID,
) {
    val name: String?
        get() = if (firstName.isNullOrBlank() && lastName.isNullOrBlank()) {
            null
        } else if (firstName.isNullOrBlank() && !lastName.isNullOrBlank()) {
            "$lastName"
        } else if (lastName.isNullOrBlank() && !firstName.isNullOrBlank()) {
            "$firstName"
        } else {
            "$firstName $lastName"
        }

    enum class AuthProvider { GOOGLE, EMAIL, }
}

data class UserWithPassword(val user: User, val password: String = "")

data class UserWithPhoto(val user: User, val photo: Uri) {
    constructor(user: User, photo: File) : this(user, Uri.fromFile(photo))
}

data class UserWithToken(
    @Embedded val u: User,
    @Relation(parentColumn = "id", entityColumn = "id")
    val token: Token,
) {
    val user: User
        get() = u.copy(token = token)
}