package com.xently.auth.common.ui.verification.di

import androidx.lifecycle.ViewModel
import com.xently.auth.common.ui.signup.SignupViewModel
import com.xently.auth.common.ui.verification.VerificationViewModel
import com.xently.common.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class VerificationModule {
    @Binds
    @IntoMap
    @ViewModelKey(VerificationViewModel::class)
    abstract fun bindVerificationViewModel(viewModel: VerificationViewModel): ViewModel
}