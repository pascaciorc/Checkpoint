package com.pascaciorc.checkpoint.di

import android.content.Context
import com.pascaciorc.checkpoint.data.AppDatabase
import com.pascaciorc.checkpoint.data.CheckpointDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return AppDatabase.getInstance(context)
    }

    @Provides
    fun provideCheckpointDao(appDatabase: AppDatabase): CheckpointDao {
        return appDatabase.checkpointDao()
    }
}