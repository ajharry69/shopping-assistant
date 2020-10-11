package com.xently.user.common.source

import com.xently.common.data.TaskResult.Error
import com.xently.common.data.TaskResult.Success
import com.xently.data.source.local.daos.UserDAO
import com.xently.models.User
import com.xently.models.UserWithPassword
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

    override fun signUp(user: UserWithPassword, photo: File?) =
        signIn(user.user.username, user.password)

    override fun signOut() = flow { emit(Success(dao.removeUser())) }
}