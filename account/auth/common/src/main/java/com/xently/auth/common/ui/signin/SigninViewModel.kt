package com.xently.auth.common.ui.signin

import com.xently.user.common.AbstractUserViewModel
import com.xently.user.common.repository.AbstractUserRepository
import javax.inject.Inject

class SigninViewModel @Inject constructor(private val repository: AbstractUserRepository) :
    AbstractUserViewModel(repository) {
    // TODO: Implement the ViewModel
}