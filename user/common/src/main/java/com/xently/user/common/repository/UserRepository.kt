package com.xently.user.common.repository

import com.xently.common.di.qualifiers.coroutines.IODispatcher
import com.xently.models.User
import com.xently.user.common.di.qualifiers.LocalUserDataSource
import com.xently.user.common.di.qualifiers.RemoteUserDataSource
import com.xently.user.common.source.IUserDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(
    @LocalUserDataSource
    private val local: IUserDataSource,
    @RemoteUserDataSource
    private val remote: IUserDataSource,
    @IODispatcher
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
) : IUserRepository {
    override fun getUser(id: Long): Flow<User?> = local.getUser(id)
}