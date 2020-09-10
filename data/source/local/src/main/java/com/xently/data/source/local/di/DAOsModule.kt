package com.xently.data.source.local.di

import com.xently.data.source.local.AssistantDatabase
import com.xently.data.source.local.daos.TokenDAO
import com.xently.data.source.local.daos.UserDAO
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
object DAOsModule {
    @Provides
    fun provideUserDAO(db: AssistantDatabase): UserDAO = db.userDAO

    @Provides
    fun provideTokenDAO(db: AssistantDatabase): TokenDAO = db.tokenDAO
}