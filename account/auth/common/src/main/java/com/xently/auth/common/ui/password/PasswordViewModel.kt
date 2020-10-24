package com.xently.auth.common.ui.password

import com.xently.user.common.AbstractUserViewModel
import com.xently.user.common.repository.AbstractUserRepository

abstract class PasswordViewModel(private val repository: AbstractUserRepository) :
    AbstractUserViewModel(repository) {
    // TODO: Implement the ViewModel
}