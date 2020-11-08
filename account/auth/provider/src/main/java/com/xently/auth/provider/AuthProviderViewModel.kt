package com.xently.auth.provider

import androidx.hilt.lifecycle.ViewModelInject
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.xently.common.data.TaskResult
import com.xently.models.user.User
import com.xently.models.user.UserWithPassword
import com.xently.user.common.AbstractUserViewModel
import com.xently.user.common.repository.AbstractUserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf

class AuthProviderViewModel @ViewModelInject constructor(private val repository: AbstractUserRepository) :
    AbstractUserViewModel(repository) {
    fun signIn(account: GoogleSignInAccount): Flow<TaskResult<User>> {
        val user = account.user
        return repository.signIn(user.user.username, user.password).flatMapLatest {
            if (it is TaskResult.Error) {
                // TODO: 1st check if error is of account not found type
                repository.signUp(user)
            } else flowOf(it)
        }
    }

    private val GoogleSignInAccount.user: UserWithPassword
        get() {
            var (firstName, lastName) = Pair<String?, String?>(null, null)
            (displayName ?: familyName ?: givenName)?.apply {
                val names = split(Regex("\\s+"), 2)
                firstName = names[0]
                if (names.size > 1) lastName = names[1]
            }
            val user = User(
                firstName = firstName,
                lastName = lastName,
                email = email.toString(),
                provider = User.AuthProvider.GOOGLE,
                photoUrl = if (photoUrl == null) null else photoUrl.toString()
            )

            return UserWithPassword(user, id.toString())
        }
}