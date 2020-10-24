package com.xently.auth.common.ui.password.reset

import com.xently.auth.common.ui.password.PasswordViewModel
import com.xently.user.common.repository.AbstractUserRepository
import javax.inject.Inject

class ResetPasswordViewModel @Inject constructor(repository: AbstractUserRepository) :
    PasswordViewModel(repository) {
    // TODO: Implement the ViewModel
}