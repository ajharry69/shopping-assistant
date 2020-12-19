package com.xently.models

import com.xently.models.user.UserWithPassword

interface UserConversion {
    fun toUserWithPassword(): UserWithPassword = UserWithPassword()
}