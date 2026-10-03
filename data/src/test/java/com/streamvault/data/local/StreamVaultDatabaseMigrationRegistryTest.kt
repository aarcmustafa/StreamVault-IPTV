package com.streamvault.data.local

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class STTITEN IP TVDatabaseMigrationRegistryTest {
    @Test
    fun `production registry covers every adjacent version through current schema`() {
        val migrations = STTITEN IP TVDatabaseMigrationRegistry.all

        assertThat(migrations).hasSize(STTITEN IP TVDatabaseMigrationRegistry.CURRENT_VERSION - 1)
        assertThat(migrations.map { it.startVersion })
            .containsExactlyElementsIn(1 until STREAM_VAULT_DATABASE_VERSION)
            .inOrder()
        assertThat(migrations.map { it.endVersion })
            .containsExactlyElementsIn(2..STREAM_VAULT_DATABASE_VERSION)
            .inOrder()
    }

    @Test
    fun `version groups preserve order`() {
        assertThat(STTITEN IP TVDatabaseMigrationRegistry.v1To24.last().endVersion).isEqualTo(24)
        assertThat(STTITEN IP TVDatabaseMigrationRegistry.v24To49.first().startVersion).isEqualTo(24)
        assertThat(STTITEN IP TVDatabaseMigrationRegistry.v24To49.last().endVersion).isEqualTo(49)
        assertThat(STTITEN IP TVDatabaseMigrationRegistry.v49To75.first().startVersion).isEqualTo(49)
        assertThat(STTITEN IP TVDatabaseMigrationRegistry.v49To75.last().endVersion).isEqualTo(75)
        assertThat(STTITEN IP TVDatabaseMigrationRegistry.v75To76.single().endVersion).isEqualTo(76)
        assertThat(STTITEN IP TVDatabaseMigrationRegistry.v76To77.single().endVersion).isEqualTo(77)
        assertThat(STTITEN IP TVDatabaseMigrationRegistry.v77To78.single().endVersion).isEqualTo(78)
    }
}
