package com.streamvault.app.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.framework.FrameworkSQLiteOpenHelperFactory
import com.streamvault.app.BuildConfig
import com.streamvault.data.local.STTITEN IP TVDatabase
import com.streamvault.data.local.STTITEN IP TVDatabaseMigrationRegistry
import com.streamvault.data.local.dao.*
import com.streamvault.data.remote.jellyfin.JellyfinProvider
import com.google.gson.Gson
import okhttp3.OkHttpClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    private const val DEBUG_SLOW_QUERY_THRESHOLD_MS = 100L

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): STTITEN IP TVDatabase =
        Room.databaseBuilder(
            context,
            STTITEN IP TVDatabase::class.java,
            "streamvault.db"
        )
            .setJournalMode(RoomDatabase.JournalMode.WRITE_AHEAD_LOGGING)
            .openHelperFactory(
                if (BuildConfig.DEBUG) {
                    SlowQueryLoggingOpenHelperFactory(
                        delegate = FrameworkSQLiteOpenHelperFactory(),
                        slowQueryThresholdMs = DEBUG_SLOW_QUERY_THRESHOLD_MS
                    )
                } else {
                    FrameworkSQLiteOpenHelperFactory()
                }
            )
            .addMigrations(*STTITEN IP TVDatabaseMigrationRegistry.all.toTypedArray())
            // NOTE: fallbackToDestructiveMigration() intentionally removed.
            // All future schema changes MUST add a corresponding Migration in STTITEN IP TVDatabase.
            .build()

    @Provides @Singleton
    fun provideJellyfinProvider(okHttpClient: OkHttpClient, gson: Gson): JellyfinProvider = JellyfinProvider(okHttpClient, gson)

    @Provides fun provideProviderDao(db: STTITEN IP TVDatabase): ProviderDao = db.providerDao()
    @Provides fun provideProviderSnapshotDao(db: STTITEN IP TVDatabase): ProviderSnapshotDao = db.providerSnapshotDao()
    @Provides fun provideChannelDao(db: STTITEN IP TVDatabase): ChannelDao = db.channelDao()
    @Provides fun provideChannelPreferenceDao(db: STTITEN IP TVDatabase): ChannelPreferenceDao = db.channelPreferenceDao()
    @Provides fun provideMovieDao(db: STTITEN IP TVDatabase): MovieDao = db.movieDao()
    @Provides fun provideSeriesDao(db: STTITEN IP TVDatabase): SeriesDao = db.seriesDao()
    @Provides fun provideEpisodeDao(db: STTITEN IP TVDatabase): EpisodeDao = db.episodeDao()
    @Provides fun provideCategoryDao(db: STTITEN IP TVDatabase): CategoryDao = db.categoryDao()
    @Provides fun provideCatalogSyncDao(db: STTITEN IP TVDatabase): CatalogSyncDao = db.catalogSyncDao()
    @Provides fun provideProgramDao(db: STTITEN IP TVDatabase): ProgramDao = db.programDao()
    @Provides fun provideFavoriteDao(db: STTITEN IP TVDatabase): FavoriteDao = db.favoriteDao()
    @Provides fun provideVirtualGroupDao(db: STTITEN IP TVDatabase): VirtualGroupDao = db.virtualGroupDao()
    @Provides fun providePlaybackHistoryDao(db: STTITEN IP TVDatabase): PlaybackHistoryDao = db.playbackHistoryDao()
    @Provides fun provideTmdbIdentityDao(db: STTITEN IP TVDatabase): TmdbIdentityDao = db.tmdbIdentityDao()
    @Provides fun provideSearchHistoryDao(db: STTITEN IP TVDatabase): SearchHistoryDao = db.searchHistoryDao()
    @Provides fun provideSearchDao(db: STTITEN IP TVDatabase): SearchDao = db.searchDao()
    @Provides fun provideSyncMetadataDao(db: STTITEN IP TVDatabase): SyncMetadataDao = db.syncMetadataDao()
    @Provides fun provideMovieCategoryHydrationDao(db: STTITEN IP TVDatabase): MovieCategoryHydrationDao = db.movieCategoryHydrationDao()
    @Provides fun provideSeriesCategoryHydrationDao(db: STTITEN IP TVDatabase): SeriesCategoryHydrationDao = db.seriesCategoryHydrationDao()
    @Provides fun provideVodCategoryHydrationDao(db: STTITEN IP TVDatabase): VodCategoryHydrationDao = db.vodCategoryHydrationDao()
    @Provides fun provideVodCatalogEntryDao(db: STTITEN IP TVDatabase): VodCatalogEntryDao = db.vodCatalogEntryDao()
    @Provides fun provideEpgSourceDao(db: STTITEN IP TVDatabase): EpgSourceDao = db.epgSourceDao()
    @Provides fun provideProviderEpgSourceDao(db: STTITEN IP TVDatabase): ProviderEpgSourceDao = db.providerEpgSourceDao()
    @Provides fun provideEpgChannelDao(db: STTITEN IP TVDatabase): EpgChannelDao = db.epgChannelDao()
    @Provides fun provideEpgProgrammeDao(db: STTITEN IP TVDatabase): EpgProgrammeDao = db.epgProgrammeDao()
    @Provides fun provideChannelEpgMappingDao(db: STTITEN IP TVDatabase): ChannelEpgMappingDao = db.channelEpgMappingDao()
    @Provides fun provideCombinedM3uProfileDao(db: STTITEN IP TVDatabase): CombinedM3uProfileDao = db.combinedM3uProfileDao()
    @Provides fun provideCombinedM3uProfileMemberDao(db: STTITEN IP TVDatabase): CombinedM3uProfileMemberDao = db.combinedM3uProfileMemberDao()
    @Provides fun provideRecordingScheduleDao(db: STTITEN IP TVDatabase): RecordingScheduleDao = db.recordingScheduleDao()
    @Provides fun provideRecordingRunDao(db: STTITEN IP TVDatabase): RecordingRunDao = db.recordingRunDao()
    @Provides fun provideProgramReminderDao(db: STTITEN IP TVDatabase): ProgramReminderDao = db.programReminderDao()
    @Provides fun provideRecordingStorageDao(db: STTITEN IP TVDatabase): RecordingStorageDao = db.recordingStorageDao()
    @Provides fun providePlaybackCompatibilityDao(db: STTITEN IP TVDatabase): PlaybackCompatibilityDao = db.playbackCompatibilityDao()
    @Provides fun provideXtreamContentIndexDao(db: STTITEN IP TVDatabase): XtreamContentIndexDao = db.xtreamContentIndexDao()
    @Provides fun provideXtreamIndexJobDao(db: STTITEN IP TVDatabase): XtreamIndexJobDao = db.xtreamIndexJobDao()
    @Provides fun provideXtreamLiveOnboardingDao(db: STTITEN IP TVDatabase): XtreamLiveOnboardingDao = db.xtreamLiveOnboardingDao()
    @Provides fun provideStalkerIndexJobDao(db: STTITEN IP TVDatabase): StalkerIndexJobDao = db.stalkerIndexJobDao()
    @Provides fun provideStalkerPortalStateDao(db: STTITEN IP TVDatabase): StalkerPortalStateDao = db.stalkerPortalStateDao()
    @Provides fun provideStalkerRemoteIdentityDao(db: STTITEN IP TVDatabase): StalkerRemoteIdentityDao = db.stalkerRemoteIdentityDao()
    @Provides fun provideStalkerDiscoveryStageDao(db: STTITEN IP TVDatabase): StalkerDiscoveryStageDao = db.stalkerDiscoveryStageDao()
    @Provides fun provideDownloadDao(db: STTITEN IP TVDatabase): DownloadDao = db.downloadDao()
    @Provides fun provideProviderDeletionCleanupDao(db: STTITEN IP TVDatabase): ProviderDeletionCleanupDao = db.providerDeletionCleanupDao()
    @Provides fun provideProviderConfigRevisionDao(db: STTITEN IP TVDatabase): ProviderConfigRevisionDao = db.providerConfigRevisionDao()
    @Provides fun provideBackupRestoreCheckpointDao(db: STTITEN IP TVDatabase): BackupRestoreCheckpointDao = db.backupRestoreCheckpointDao()
    @Provides fun provideBackupRestoreLedgerDao(db: STTITEN IP TVDatabase): BackupRestoreLedgerDao = db.backupRestoreLedgerDao()
    @Provides fun provideProviderWorkflowDao(db: STTITEN IP TVDatabase): ProviderWorkflowDao = db.providerWorkflowDao()
    @Provides fun provideM3uClassificationDao(db: STTITEN IP TVDatabase): M3uClassificationDao = db.m3uClassificationDao()
    @Provides fun providePluginProviderOwnershipDao(db: STTITEN IP TVDatabase): PluginProviderOwnershipDao = db.pluginProviderOwnershipDao()
}
