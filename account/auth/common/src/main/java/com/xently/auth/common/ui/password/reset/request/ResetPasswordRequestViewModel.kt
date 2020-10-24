package com.xently.auth.common.ui.password.reset.request

import com.xently.auth.common.ui.password.PasswordViewModel
import com.xently.user.common.repository.AbstractUserRepository
import javax.inject.Inject

class ResetPasswordRequestViewModel @Inject constructor(repository: AbstractUserRepository) :
    PasswordViewModel(repository) {
    // TODO: Implement the ViewModel
}