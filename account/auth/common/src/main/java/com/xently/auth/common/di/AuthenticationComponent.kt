package com.xently.auth.common.di

import android.content.Context
import com.xently.auth.common.ui.signin.di.SigninComponent
import com.xently.common.di.modules.ViewModelFactoryModule
import com.xently.shoppingassistant.di.AuthenticationModuleDependencies
import dagger.BindsInstance
import dagger.Component

@Component(
    dependencies = [AuthenticationModuleDependencies::class],
    modules = [ViewModelFactoryModule::class, AuthenticationSubcomponentsModule::class]
)
interface AuthenticationComponent {
    @Component.Builder
    interface Builder {
        fun bindContext(@BindsInstance context: Context): Builder
        fun appDependencies(authenticationModuleDependencies: AuthenticationModuleDependencies): Builder
        fun build(): AuthenticationComponent
    }

    fun signinComponentFactory(): SigninComponent.Factory
}