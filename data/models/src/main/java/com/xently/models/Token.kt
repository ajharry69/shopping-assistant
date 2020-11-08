package com.xently.models

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.xently.common.utils.Exclude
import com.xently.common.utils.web.AuthScheme
import com.xently.common.utils.web.AuthScheme.BEARER
import com.xently.models.user.DEFAULT_USER_ID

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
    val scheme: AuthScheme = BEARER,
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readLong(),
        AuthScheme.valueOf(parcel.readString() ?: BEARER.name),
    )

    override fun toString(): String = "$scheme $access"

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        with(parcel) {
            writeString(access)
            writeString(refresh)
            writeLong(id)
            writeString(scheme.name)
        }
    }

    override fun describeContents(): Int = 0

    companion object CREATOR : Parcelable.Creator<Token> {
        override fun createFromParcel(parcel: Parcel): Token = Token(parcel)

        override fun newArray(size: Int): Array<Token?> = arrayOfNulls(size)
    }
}