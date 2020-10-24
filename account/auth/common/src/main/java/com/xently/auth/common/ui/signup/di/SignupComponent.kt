package com.xently.auth.common.ui.signup.di

import com.xently.auth.common.ui.signup.SignupFragment
import dagger.Subcomponent

@Subcomponent(modules = [SignupModule::class])
interface SignupComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(): SignupComponent
    }

    fun inject(fragment: SignupFragment)
}