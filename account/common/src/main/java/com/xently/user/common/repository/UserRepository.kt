package com.xently.user.common.repository

import android.content.SharedPreferences
import com.xently.common.data.TaskResult
import com.xently.common.data.data
import com.xently.common.di.qualifiers.UnencryptedSharedPreference
import com.xently.common.di.qualifiers.coroutines.IODispatcher
import com.xently.data.source.common.whileLoading
import com.xently.models.user.User
import com.xently.models.user.UserWithPassword
import com.xently.user.common.di.qualifiers.LocalUserDataSource
import com.xently.user.common.di.qualifiers.RemoteUserDataSource
import com.xently.user.common.source.IUserDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
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
        .signUpFlowWhileLoading()

    override fun signUp(user: UserWithPassword, photo: File?) = remote.signUp(user, photo)
        .signUpFlowWhileLoading()

    override fun signOut() = local.signOut().flatMapLatest {
        remote.signOut()
    }.whileLoading(ioDispatcher)

    override fun changeOrResetPassword(
        oldPassword: String,
        newPassword: String,
        isChange: Boolean,
    ) = remote.changeOrResetPassword(oldPassword, newPassword, isChange).flatMapLatest {
        flow {
            if (it.data == null) emit(it)
            else emitAll(local.signUp(UserWithPassword(it.data!!)))
        }
    }.whileLoading(ioDispatcher)

    override fun requestPasswordReset(email: String) =
        remote.requestPasswordReset(email).flatMapLatest {
            local.requestPasswordReset(email)
        }.whileLoading(ioDispatcher)

    override fun verifyAccount(verificationCode: String) =
        remote.verifyAccount(verificationCode).signUpFlowWhileLoading()

    override fun requestVerificationCode() = remote.requestVerificationCode().flatMapLatest {
        local.requestVerificationCode()
    }.whileLoading(ioDispatcher)

    private fun Flow<TaskResult<User>>.signUpFlowWhileLoading() =
        signUpFlow().whileLoading(ioDispatcher)

    private fun Flow<TaskResult<User>>.signUpFlow() = flatMapLatest {
        it.signUpFlow()
    }

    private fun TaskResult<User>.signUpFlow(): Flow<TaskResult<User>> = flow {
        if (data == null) emit(this@signUpFlow)
        else emitAll(local.signUp(UserWithPassword(data!!)))
    }
}