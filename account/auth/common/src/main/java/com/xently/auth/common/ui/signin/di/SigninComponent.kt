package com.xently.auth.common.ui.signin.di

import com.xently.auth.common.ui.signin.SigninFragment
import dagger.Component
import dagger.Subcomponent

@Subcomponent(modules = [SigninModule::class])
interface SigninComponent {
    fun inject(fragment: SigninFragment)

    @Subcomponent.Factory
    interface Factory {
        fun create(): SigninComponent
    }
}