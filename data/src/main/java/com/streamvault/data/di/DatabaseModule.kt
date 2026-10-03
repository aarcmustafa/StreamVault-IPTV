package com.streamvault.data.di

import android.content.Context
import androidx.room.Room
import com.streamvault.data.local.StreamVaultDatabase
import com.streamvault.data.local.RoomDatabaseTransactionRunner
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideStreamVaultDatabase(
        @ApplicationContext context: Context
    ): StreamVaultDatabase {
        return Room.databaseBuilder(
            context,
            StreamVaultDatabase::class.java,
            "streamvault.db"
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    @Singleton
    fun provideDatabaseTransactionRunner(
        database: StreamVaultDatabase
    ): RoomDatabaseTransactionRunner {
        return RoomDatabaseTransactionRunner(database)
    }
}
