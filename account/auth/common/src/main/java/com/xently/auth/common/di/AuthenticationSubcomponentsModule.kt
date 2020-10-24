package com.xently.auth.common.di

import com.xently.auth.common.ui.signin.di.SigninComponent
import dagger.Module

@Module(
    subcomponents = [
        SigninComponent::class
    ]
)
object AuthenticationSubcomponentsModule