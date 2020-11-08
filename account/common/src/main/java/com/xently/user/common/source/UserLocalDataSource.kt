package com.xently.user.common.source

import com.xently.common.data.TaskResult
import com.xently.common.data.TaskResult.Error
import com.xently.common.data.TaskResult.Success
import com.xently.data.source.local.daos.UserDAO
import com.xently.models.Token
import com.xently.models.user.User
import com.xently.models.user.UserWithPassword
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import java.io.File
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserLocalDataSource @Inject constructor(private val dao: UserDAO) : IUserDataSource {
    override fun getUser(id: Long): Flow<User?> = dao.getUser(id)

    override fun signIn(username: String, password: String) = getUser().flatMapLatest {
        val result = if (it == null) Error("Invalid username and/or password") else Success(it)
        flowOf(result)
    }

    override fun signUp(user: UserWithPassword, photo: File?) = flow {
        dao.addUser(user.user)
        emit(Success(user.user))
    }

    override fun signOut() = flow { emit(Success(dao.removeUser())) }

    override fun changeOrResetPassword(
        oldPassword: String,
        newPassword: String,
        isChange: Boolean,
    ): Flow<TaskResult<User?>> {
        TODO("Not yet implemented")
    }

    override fun requestPasswordReset(email: String): Flow<TaskResult<Token>> {
        TODO("Not yet implemented")
    }

    override fun verifyAccount(verificationCode: String): Flow<TaskResult<User>> {
        TODO("Not yet implemented")
    }

    override fun requestVerificationCode(): Flow<TaskResult<Token>> {
        TODO("Not yet implemented")
    }
}