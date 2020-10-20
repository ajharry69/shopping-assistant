package com.xently.user.common.repository

import android.content.SharedPreferences
import com.xently.common.data.TaskResult
import com.xently.common.data.data
import com.xently.common.di.qualifiers.UnencryptedSharedPreference
import com.xently.common.di.qualifiers.coroutines.IODispatcher
import com.xently.data.source.common.withLoading
import com.xently.models.User
import com.xently.models.UserWithPassword
import com.xently.user.common.di.qualifiers.LocalUserDataSource
import com.xently.user.common.di.qualifiers.RemoteUserDataSource
import com.xently.user.common.source.IUserDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import java.io.File
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(
    @LocalUserDataSource
    private val local: IUserDataSource,
    @RemoteUserDataSource
    private val remote: IUserDataSource,
    @UnencryptedSharedPreference
    private val preference: SharedPreferences,
    @IODispatcher
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
) : AbstractUserRepository(preference) {
    override fun getUser(id: Long): Flow<User?> = local.getUser(id)

    override fun signIn(username: String, password: String) = remote.signIn(username, password)
        .flatMapLatest {
            it.dataFlow(password)
        }.withLoading().flowOn(ioDispatcher)

    override fun signUp(user: UserWithPassword, photo: File?) = remote.signUp(user, photo)
        .flatMapLatest {
            it.dataFlow()
        }.withLoading().flowOn(ioDispatcher)

    override fun signOut() = local.signOut().flatMapLatest {
        remote.signOut()
    }.withLoading().flowOn(ioDispatcher)

    private fun TaskResult<User>.dataFlow(password: String = ""): Flow<TaskResult<User>> = flow {
        if (data == null) emit(this@dataFlow)
        else emitAll(local.signUp(UserWithPassword(data!!, password)))
    }
}