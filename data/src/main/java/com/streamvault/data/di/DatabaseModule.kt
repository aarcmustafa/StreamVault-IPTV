package com.streamvault.data.di

import android.content.Context
import androidx.room.Room
import com.streamvault.data.local.STTITEN_IP_TVDatabase
import com.streamvault.data.local.dao.ChannelDao
import com.streamvault.data.local.dao.EpgProgrammeDao
import com.streamvault.data.local.dao.EpisodeDao
import com.streamvault.data.local.dao.FavoriteDao
import com.streamvault.data.local.dao.ProgramDao
import com.streamvault.data.local.dao.ProgramReminderDao
import com.streamvault.data.local.dao.SearchHistoryDao
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
    fun provideDatabase(@ApplicationContext context: Context): STTITEN_IP_TVDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            STTITEN_IP_TVDatabase::class.java,
            "sttiten_iptv.db"
        ).fallbackToDestructiveMigration().build()
    }

    @Provides fun provideChannelDao(db: STTITEN_IP_TVDatabase): ChannelDao = db.channelDao()
    @Provides fun provideProgramDao(db: STTITEN_IP_TVDatabase): ProgramDao = db.programDao()
    @Provides fun provideEpgProgrammeDao(db: STTITEN_IP_TVDatabase): EpgProgrammeDao = db.epgProgrammeDao()
    @Provides fun provideEpisodeDao(db: STTITEN_IP_TVDatabase): EpisodeDao = db.episodeDao()
    @Provides fun provideFavoriteDao(db: STTITEN_IP_TVDatabase): FavoriteDao = db.favoriteDao()
    @Provides fun provideProgramReminderDao(db: STTITEN_IP_TVDatabase): ProgramReminderDao = db.programReminderDao()
    @Provides fun provideSearchHistoryDao(db: STTITEN_IP_TVDatabase): SearchHistoryDao = db.searchHistoryDao()
}
