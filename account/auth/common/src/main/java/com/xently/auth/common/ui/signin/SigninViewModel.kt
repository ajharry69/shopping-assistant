package com.xently.auth.common.ui.signin

import com.xently.auth.common.ui.signup.SignupViewModel
import com.xently.user.common.repository.AbstractUserRepository
import javax.inject.Inject

class SigninViewModel @Inject constructor(private val repository: AbstractUserRepository) :
    SignupViewModel(repository) {
    fun signIn(username: String, password: String) = repository.signIn(username, password)
}