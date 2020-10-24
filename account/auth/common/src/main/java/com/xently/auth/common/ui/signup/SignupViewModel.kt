package com.xently.auth.common.ui.signup

import com.xently.models.UserWithPassword
import com.xently.user.common.AbstractUserViewModel
import com.xently.user.common.repository.AbstractUserRepository
import java.io.File
import javax.inject.Inject

open class SignupViewModel @Inject constructor(private val repository: AbstractUserRepository) :
    AbstractUserViewModel(repository) {
    fun signUp(user: UserWithPassword, photo: File? = null) = repository.signUp(user, photo)
}