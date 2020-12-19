package com.xently.user.common.source

import com.xently.common.data.TaskResult
import com.xently.models.Token
import com.xently.models.user.DEFAULT_USER_ID
import com.xently.models.user.User
import com.xently.models.user.UserWithPassword
import kotlinx.coroutines.flow.Flow
import java.io.File

interface IUserDataSource {
    fun getUser(id: Long = DEFAULT_USER_ID): Flow<User?>

    fun signIn(username: String, password: String): Flow<TaskResult<User>> {
        TODO("Not yet implemented")
    }

    fun signUp(user: UserWithPassword, photo: File? = null): Flow<TaskResult<User>>

    fun signOut(): Flow<TaskResult<Unit>>

    fun changeOrResetPassword(
        oldPassword: String,
        newPassword: String,
        isChange: Boolean = false,
    ): Flow<TaskResult<User?>> {
        TODO("Not yet implemented")
    }

    fun requestPasswordReset(email: String): Flow<TaskResult<Token>> {
        TODO("Not yet implemented")
    }

    fun verifyAccount(verificationCode: String): Flow<TaskResult<User>> {
        TODO("Not yet implemented")
    }

    fun requestVerificationCode(): Flow<TaskResult<Token>> {
        TODO("Not yet implemented")
    }
}