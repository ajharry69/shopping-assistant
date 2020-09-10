package com.xently.user.common.di

import com.xently.common.di.qualifiers.coroutines.IODispatcher
import com.xently.user.common.di.qualifiers.LocalUserDataSource
import com.xently.user.common.di.qualifiers.RemoteUserDataSource
import com.xently.user.common.repository.IUserRepository
import com.xently.user.common.repository.UserRepository
import com.xently.user.common.source.IUserDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideUserRepository(
        @LocalUserDataSource
        local: IUserDataSource,
        @RemoteUserDataSource
        remote: IUserDataSource,
        @IODispatcher ioDispatcher: CoroutineDispatcher,
    ): IUserRepository = UserRepository(local, remote, ioDispatcher)
}