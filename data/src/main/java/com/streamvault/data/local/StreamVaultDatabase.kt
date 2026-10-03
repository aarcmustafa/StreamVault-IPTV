package com.streamvault.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [], // سيتم إضافة الكيانات تباعاً مع تطوير الجداول
    version = 1,
    exportSchema = false
)
abstract class StreamVaultDatabase : RoomDatabase() {
    // يمكن تعريف DAOs هنا لاحقاً
}
