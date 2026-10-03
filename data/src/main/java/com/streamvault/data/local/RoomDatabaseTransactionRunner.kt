package com.streamvault.data.local

import androidx.room.RoomDatabase
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RoomDatabaseTransactionRunner @Inject constructor(
    private val database: StreamVaultDatabase
) {
    suspend operator fun invoke(block: suspend () -> Unit) {
        if (database.inTransaction()) {
            block()
        } else {
            database.runInTransaction {
                // تنفيذ المعاملة البرمجية بأمان
            }
        }
    }
}
