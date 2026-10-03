package com.streamvault.data.sync

import android.util.Log
import androidx.annotation.WorkerThread
import com.streamvault.data.local.STTITEN_IP_TVDatabase
import com.streamvault.data.local.dao.ChannelDao
import com.streamvault.data.local.dao.EpgProgrammeDao
import com.streamvault.data.local.dao.EpisodeDao
import com.streamvault.data.local.dao.FavoriteDao
import com.streamvault.data.local.dao.ProgramDao
import com.streamvault.data.local.dao.ProgramReminderDao
import com.streamvault.data.local.dao.SearchHistoryDao
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DatabaseMaintenanceManager @Inject constructor(
    private val database: STTITEN_IP_TVDatabase,
    private val channelDao: ChannelDao,
    private val programDao: ProgramDao,
    private val epgProgrammeDao: EpgProgrammeDao,
    private val episodeDao: EpisodeDao,
    private val favoriteDao: FavoriteDao,
    private val programReminderDao: ProgramReminderDao,
    private val searchHistoryDao: SearchHistoryDao
) {
    @WorkerThread
    suspend fun clearOldData() {
        try {
            if (database.isOpen) {
                Log.d("DatabaseMaintenance", "Running maintenance cleanup...")
            }
        } catch (e: Exception) {
            Log.e("DatabaseMaintenance", "Error during maintenance", e)
        }
    }
}
