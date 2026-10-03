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
import com.streamvault.data.local.entity.ChannelEntity
import com.streamvault.data.local.entity.EpgProgrammeEntity
import com.streamvault.data.local.entity.EpisodeEntity
import com.streamvault.data.local.entity.FavoriteEntity
import com.streamvault.data.local.entity.ProgramEntity
import com.streamvault.data.local.entity.ProgramReminderEntity
import com.streamvault.data.local.entity.SearchHistoryEntity
import com.streamvault.data.local.entity.ProviderEntity
import com.streamvault.data.local.entity.SeriesEntity
import com.streamvault.data.local.entity.VirtualGroupEntity
import com.streamvault.data.local.entity.CategoryEntity
import com.streamvault.data.local.entity.PlaybackHistoryEntity
import com.streamvault.data.local.entity.MovieEntity
import com.streamvault.data.local.entity.EpgSourceEntity
import com.streamvault.data.local.entity.ChannelEpgMappingEntity
import com.streamvault.data.local.entity.ChannelsFtsEntity

@Database(
    entities = [
        ChannelEntity::class,
        ProgramEntity::class,
        EpgProgrammeEntity::class,
        EpisodeEntity::class,
        FavoriteEntity::class,
        ProgramReminderEntity::class,
        SearchHistoryEntity::class,
        ProviderEntity::class,
        SeriesEntity::class,
        VirtualGroupEntity::class,
        CategoryEntity::class,
        PlaybackHistoryEntity::class,
        MovieEntity::class,
        EpgSourceEntity::class,
        ChannelEpgMappingEntity::class,
        ChannelsFtsEntity::class
    ],
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
