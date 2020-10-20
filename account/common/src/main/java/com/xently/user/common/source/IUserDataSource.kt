package com.xently.user.common.source

import com.xently.common.data.TaskResult
import com.xently.models.DEFAULT_USER_ID
import com.xently.models.User
import com.xently.models.UserWithPassword
import kotlinx.coroutines.flow.Flow
import java.io.File

interface IUserDataSource {
    fun getUser(id: Long = DEFAULT_USER_ID): Flow<User?>

    fun signIn(username: String, password: String): Flow<TaskResult<User>>

    fun signUp(user: UserWithPassword, photo: File? = null): Flow<TaskResult<User>>

    fun signOut(): Flow<TaskResult<Unit>>
}