package com.xently.data.source.local.di

import android.content.Context
import androidx.room.Room
import com.xently.data.source.local.AssistantDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
object StorageModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AssistantDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            AssistantDatabase::class.java,
            "assistant.db"
        ).fallbackToDestructiveMigration().build()
    }
}