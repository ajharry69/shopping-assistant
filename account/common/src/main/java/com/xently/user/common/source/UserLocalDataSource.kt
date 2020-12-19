package com.xently.user.common.source

import com.xently.common.data.TaskResult.Success
import com.xently.data.source.local.daos.UserDAO
import com.xently.models.user.User
import com.xently.models.user.UserWithPassword
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.File
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserLocalDataSource @Inject constructor(private val dao: UserDAO) : IUserDataSource {
    override fun getUser(id: Long): Flow<User?> = dao.getUser(id)

    override fun signUp(user: UserWithPassword, photo: File?) = flow {
        dao.addUser(user.user)
        emit(Success(user.user))
    }

    override fun signOut() = flow { emit(Success(dao.removeUser())) }
}