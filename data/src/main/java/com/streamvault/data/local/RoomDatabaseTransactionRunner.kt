package com.streamvault.data.local

import androidx.room.withTransaction
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RoomDatabaseTransactionRunner @Inject constructor(
    private val database: STTITEN_IP_TVDatabase
) {
    suspend operator fun invoke(block: suspend () -> Unit) {
        database.withTransaction {
            block()
        }
    }
}
