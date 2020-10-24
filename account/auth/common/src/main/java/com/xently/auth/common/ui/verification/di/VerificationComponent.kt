package com.xently.auth.common.ui.verification.di

import com.xently.auth.common.ui.verification.VerificationFragment
import dagger.Subcomponent

@Subcomponent(modules = [VerificationModule::class])
interface VerificationComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(): VerificationComponent
    }

    fun inject(fragment: VerificationFragment)
}