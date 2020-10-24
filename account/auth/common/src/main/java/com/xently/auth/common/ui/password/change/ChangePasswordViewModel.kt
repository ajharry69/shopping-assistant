package com.xently.auth.common.ui.password.change

import com.xently.auth.common.ui.password.PasswordViewModel
import com.xently.user.common.repository.AbstractUserRepository
import javax.inject.Inject

class ChangePasswordViewModel @Inject constructor(repository: AbstractUserRepository) :
    PasswordViewModel(repository) {
    // TODO: Implement the ViewModel
}