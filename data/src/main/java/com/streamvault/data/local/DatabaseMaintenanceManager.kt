package com.streamvault.data.local

import android.util.Log
import androidx.annotation.WorkerThread
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DatabaseMaintenanceManager @Inject constructor(
    private val database: StreamVaultDatabase
) {
    @WorkerThread
    suspend fun clearOldData() {
        try {
            if (database.isOpen) {
                Log.d("DatabaseMaintenance", "Running maintenance...")
            }
        } catch (e: Exception) {
            Log.e("DatabaseMaintenance", "Error during maintenance", e)
        }
    }
}
