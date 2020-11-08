package com.xently.models.user

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.xently.common.utils.Exclude
import com.xently.models.Token

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
) : Parcelable {
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

    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        AuthProvider.valueOf(parcel.readString()!!),
        parcel.readString(),
        parcel.readByte() != 0.toByte(),
        parcel.readParcelable(Token::class.java.classLoader)!!,
        parcel.readLong())

    enum class AuthProvider { GOOGLE, EMAIL, }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        with(parcel) {
            writeString(username)
            writeString(email)
            writeString(firstName)
            writeString(lastName)
            writeString(provider.name)
            writeString(photoUrl)
            writeByte(if (isVerified) 1 else 0)
            writeParcelable(token, flags)
            writeLong(id)
        }
    }

    override fun describeContents(): Int = 0

    companion object CREATOR : Parcelable.Creator<User> {
        override fun createFromParcel(parcel: Parcel): User = User(parcel)

        override fun newArray(size: Int): Array<User?> = arrayOfNulls(size)
    }
}