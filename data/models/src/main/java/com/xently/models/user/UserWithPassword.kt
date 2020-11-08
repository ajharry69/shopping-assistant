package com.xently.models.user

import android.os.Parcel
import android.os.Parcelable

data class UserWithPassword(val user: User, val password: String = "") : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readParcelable(User::class.java.classLoader)!!,
        parcel.readString()!!)

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        with(parcel) {
            writeParcelable(user, flags)
            writeString(password)
        }
    }

    override fun describeContents(): Int = 0

    companion object CREATOR : Parcelable.Creator<UserWithPassword> {
        override fun createFromParcel(parcel: Parcel): UserWithPassword = UserWithPassword(parcel)

        override fun newArray(size: Int): Array<UserWithPassword?> = arrayOfNulls(size)
    }
}