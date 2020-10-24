package com.xently.shoppingassistant.di

import com.xently.user.common.repository.AbstractUserRepository
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@EntryPoint
@InstallIn(ApplicationComponent::class)
interface AuthenticationModuleDependencies {
    val userRepository: AbstractUserRepository
}