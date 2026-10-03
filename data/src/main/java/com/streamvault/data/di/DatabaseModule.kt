package com.streamvault.data.di

import android.content.Context
import androidx.room.Room
import com.streamvault.data.local.STTITEN_IP_TVDatabase
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
    fun provideSTTITENIPTVDatabase(
        @ApplicationContext context: Context
    ): STTITEN_IP_TVDatabase {
        return Room.databaseBuilder(
            context,
            STTITEN_IP_TVDatabase::class.java,
            "sttiten_iptv.db"
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    @Singleton
    fun provideDatabaseTransactionRunner(
        database: STTITEN_IP_TVDatabase
    ): RoomDatabaseTransactionRunner {
        return RoomDatabaseTransactionRunner(database)
    }
}
