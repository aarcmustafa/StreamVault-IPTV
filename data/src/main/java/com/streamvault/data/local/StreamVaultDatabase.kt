package com.streamvault.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [],
    version = 1,
    exportSchema = false
)
abstract class StreamVaultDatabase : RoomDatabase() {
    // يمكن إضافة الـ DAOs هنا لاحقاً حسب الحاجة
}
