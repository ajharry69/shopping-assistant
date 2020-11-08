package com.xently.models.user

import android.net.Uri
import java.io.File

data class UserWithPhoto(val user: User, val photo: Uri) {
    constructor(user: User, photo: File) : this(user, Uri.fromFile(photo))
}