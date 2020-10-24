package com.xently.auth.common.ui.signup.di

import androidx.lifecycle.ViewModel
import com.xently.auth.common.ui.signup.SignupViewModel
import com.xently.common.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class SignupModule {
    @Binds
    @IntoMap
    @ViewModelKey(SignupViewModel::class)
    abstract fun bindSignupViewModel(viewModel: SignupViewModel): ViewModel
}