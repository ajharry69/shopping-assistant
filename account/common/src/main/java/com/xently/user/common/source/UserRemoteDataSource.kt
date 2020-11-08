package com.xently.user.common.source

import android.util.Base64
import com.xently.common.data.source.remote.AbstractRemoteDataSource
import com.xently.common.di.qualifiers.coroutines.IODispatcher
import com.xently.data.source.remote.services.UserService
import com.xently.models.user.User
import com.xently.models.user.UserWithPassword
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flow
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRemoteDataSource @Inject constructor(
    private val service: UserService,
    @IODispatcher
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
) : AbstractRemoteDataSource<User>(ioDispatcher), IUserDataSource {
    override fun getUser(id: Long): Flow<User?> = emptyFlow()

    override fun signIn(username: String, password: String) = flow {
        emit(sendRequest {
            val authData: String = Base64.encodeToString(
                "$username:$password".toByteArray(),
                Base64.DEFAULT or Base64.NO_WRAP
            )
            service.signIn("Basic $authData")
        })
    }

    override fun signUp(user: UserWithPassword, photo: File?) = flow {
        emit(sendRequest {
            if (photo == null) service.signUp(user) else {
                val picture =
                    MultipartBody.Part.createFormData("photo", photo.name, photo.asRequestBody())
                val person = MultipartBody.Part.createFormData("user", user.toString())
                service.signUp(person, picture)
            }
        })
    }

    override fun signOut() = flow { emit(sendRequest { service.signOut() }) }

    override fun changeOrResetPassword(
        oldPassword: String,
        newPassword: String,
        isChange: Boolean,
    ) = flow {
        emit(sendRequest {
            if (isChange) {
                service.changePassword(oldPassword, newPassword)
            } else {
                service.resetPassword(oldPassword, newPassword)
            }
        })
    }

    override fun requestPasswordReset(email: String) = flow {
        emit(sendRequest { service.resetPasswordRequest(email) })
    }

    override fun verifyAccount(verificationCode: String) = flow {
        emit(sendRequest { service.verifyAccount(verificationCode) })
    }

    override fun requestVerificationCode() = flow {
        emit(sendRequest { service.requestVerificationCode() })
    }
}