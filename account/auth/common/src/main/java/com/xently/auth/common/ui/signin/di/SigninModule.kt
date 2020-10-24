package com.xently.auth.common.ui.signin.di

import androidx.lifecycle.ViewModel
import com.xently.auth.common.ui.signin.SigninViewModel
import com.xently.common.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class SigninModule {
    @Binds
    @IntoMap
    @ViewModelKey(SigninViewModel::class)
    abstract fun bindSigninViewModel(viewModel: SigninViewModel): ViewModel
}