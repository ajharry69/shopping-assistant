package com.xently.auth.common.ui.password

import com.xently.user.common.AbstractUserViewModel
import com.xently.user.common.repository.AbstractUserRepository

abstract class PasswordViewModel(private val repository: AbstractUserRepository) :
    AbstractUserViewModel(repository) {
    fun resetPassword(oldPassword: String, newPassword: String, isChange: Boolean = false) =
        repository.changeOrResetPassword(oldPassword, newPassword, isChange)

    fun requestPasswordReset(email: String) = repository.requestPasswordReset(email)
}