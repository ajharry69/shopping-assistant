package com.xently.user.common.source

import com.xently.common.data.source.remote.AbstractRemoteDataSource
import com.xently.common.di.qualifiers.coroutines.IODispatcher
import com.xently.data.source.remote.services.UserService
import com.xently.models.User
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRemoteDataSource @Inject constructor(
    private val service: UserService,
    @IODispatcher
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
) : AbstractRemoteDataSource<User>(ioDispatcher), IUserDataSource {
    override fun getUser(id: Long): Flow<User?> = emptyFlow()
}