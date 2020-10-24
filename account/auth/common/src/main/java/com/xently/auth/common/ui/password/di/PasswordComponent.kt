package com.xently.auth.common.ui.password.di

import com.xently.auth.common.ui.password.change.ChangePasswordFragment
import com.xently.auth.common.ui.password.reset.ResetPasswordFragment
import com.xently.auth.common.ui.password.reset.request.ResetPasswordRequestFragment
import dagger.Subcomponent

@Subcomponent(modules = [PasswordModule::class])
interface PasswordComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(): PasswordComponent
    }

    fun inject(fragment: ResetPasswordFragment)
    fun inject(fragment: ResetPasswordRequestFragment)
    fun inject(fragment: ChangePasswordFragment)
}