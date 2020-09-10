package com.xently.user.common.di

import com.xently.common.di.qualifiers.coroutines.IODispatcher
import com.xently.data.source.local.daos.UserDAO
import com.xently.data.source.remote.services.UserService
import com.xently.user.common.di.qualifiers.LocalUserDataSource
import com.xently.user.common.di.qualifiers.RemoteUserDataSource
import com.xently.user.common.source.IUserDataSource
import com.xently.user.common.source.UserLocalDataSource
import com.xently.user.common.source.UserRemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object DataSourceModule {
    @Provides
    @Singleton
    @RemoteUserDataSource
    fun provideRemoteUserDataSource(
        service: UserService,
        @IODispatcher
        ioDispatcher: CoroutineDispatcher,
    ): IUserDataSource = UserRemoteDataSource(service, ioDispatcher)

    @Provides
    @Singleton
    @LocalUserDataSource
    fun provideLocalUserDataSource(dao: UserDAO): IUserDataSource = UserLocalDataSource(dao)
}