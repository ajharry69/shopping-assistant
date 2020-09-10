package com.xently.user.common.source

import com.xently.data.source.local.daos.UserDAO
import com.xently.models.User
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserLocalDataSource @Inject constructor(private val dao: UserDAO) : IUserDataSource {
    override fun getUser(id: Long): Flow<User?> = dao.getUser(id)
}