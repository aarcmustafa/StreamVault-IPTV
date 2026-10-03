package com.streamvault.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.streamvault.data.local.dao.ChannelDao
import com.streamvault.data.local.dao.EpgProgrammeDao
import com.streamvault.data.local.dao.EpisodeDao
import com.streamvault.data.local.dao.FavoriteDao
import com.streamvault.data.local.dao.ProgramDao
import com.streamvault.data.local.dao.ProgramReminderDao
import com.streamvault.data.local.dao.SearchHistoryDao

@Database(
    entities = [], 
    version = 1,
    exportSchema = false
)
abstract class STTITEN_IP_TVDatabase : RoomDatabase() {
    abstract fun channelDao(): ChannelDao
    abstract fun programDao(): ProgramDao
    abstract fun epgProgrammeDao(): EpgProgrammeDao
    abstract fun episodeDao(): EpisodeDao
    abstract fun favoriteDao(): FavoriteDao
    abstract fun programReminderDao(): ProgramReminderDao
    abstract fun searchHistoryDao(): SearchHistoryDao
}
