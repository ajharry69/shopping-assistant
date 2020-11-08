package com.xently.auth.common.ui.verification

import com.xently.user.common.AbstractUserViewModel
import com.xently.user.common.repository.AbstractUserRepository
import javax.inject.Inject

class VerificationViewModel @Inject constructor(private val repository: AbstractUserRepository) :
    AbstractUserViewModel(repository) {
    fun verifyAccount(verificationCode: String) = repository.verifyAccount(verificationCode)

    fun requestVerificationCode() = repository.requestVerificationCode()
}