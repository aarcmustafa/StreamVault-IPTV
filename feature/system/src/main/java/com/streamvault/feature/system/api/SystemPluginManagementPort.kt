package com.streamvault.feature.system.api

import android.net.Uri
import com.streamvault.domain.model.Result
import com.streamvault.domain.provider.ProviderSource
import kotlinx.serialization.json.JsonObject

interface SystemPluginManagementPort {
    suspend fun discoverPlugins(): List<InstalledSTTITEN IP TVPlugin>

    suspend fun providerSources(): List<ProviderSource>

    suspend fun installApkFromUri(uri: Uri): Result<Unit>

    suspend fun installApkFromUrl(url: String): Result<Unit>

    suspend fun setPluginEnabled(
        plugin: InstalledSTTITEN IP TVPlugin,
        enabled: Boolean,
        onProgress: (String) -> Unit,
    ): PluginActionResult

    fun openPluginConfiguration(plugin: InstalledSTTITEN IP TVPlugin): PluginActionResult

    suspend fun loadPluginConfiguration(
        plugin: InstalledSTTITEN IP TVPlugin,
    ): Result<PluginConfigurationSnapshot>

    suspend fun loadPluginConfigurationValues(plugin: InstalledSTTITEN IP TVPlugin): Result<JsonObject>

    suspend fun savePluginConfiguration(
        plugin: InstalledSTTITEN IP TVPlugin,
        valuesJson: String,
    ): PluginActionResult

    suspend fun runPluginConfigurationAction(
        plugin: InstalledSTTITEN IP TVPlugin,
        actionId: String,
    ): PluginActionResult
}
