package com.xently.auth.common.di

import com.xently.auth.common.ui.password.di.PasswordComponent
import com.xently.auth.common.ui.signin.di.SigninComponent
import com.xently.auth.common.ui.signup.di.SignupComponent
import dagger.Module

@Module(
    subcomponents = [
        SigninComponent::class,
        PasswordComponent::class,
        SignupComponent::class,
    ]
)
object AuthenticationSubcomponentsModule