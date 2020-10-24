package com.xently.auth.common.ui.password.di

import androidx.lifecycle.ViewModel
import com.xently.auth.common.ui.password.change.ChangePasswordViewModel
import com.xently.auth.common.ui.password.reset.ResetPasswordViewModel
import com.xently.auth.common.ui.password.reset.request.ResetPasswordRequestViewModel
import com.xently.common.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class PasswordModule {
    @Binds
    @IntoMap
    @ViewModelKey(ResetPasswordRequestViewModel::class)
    abstract fun bindRequestPasswordResetViewModel(viewModel: ResetPasswordRequestViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ResetPasswordViewModel::class)
    abstract fun bindResetPasswordViewModel(viewModel: ResetPasswordViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ChangePasswordViewModel::class)
    abstract fun bindChangePasswordViewModel(viewModel: ChangePasswordViewModel): ViewModel
}