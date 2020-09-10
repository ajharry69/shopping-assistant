package com.xently.user.common.source

import com.xently.models.DEFAULT_USER_ID
import com.xently.models.User
import kotlinx.coroutines.flow.Flow

interface IUserDataSource {
    fun getUser(id: Long = DEFAULT_USER_ID): Flow<User?>
}