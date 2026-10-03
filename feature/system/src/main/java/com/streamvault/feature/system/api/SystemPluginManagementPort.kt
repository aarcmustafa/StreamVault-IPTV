package com.streamvault.feature.system.api

import android.net.Uri
import kotlinx.serialization.json.JsonObject

data class PluginActionResult(
    val success: Boolean,
    val message: String = ""
)

data class PluginConfigurationSnapshot(
    val plugin: InstalledSTTITENIPTVPlugin,
    val schema: String = "",
    val values: JsonObject = JsonObject(emptyMap())
)

data class ProviderSource(
    val id: String,
    val name: String
)

interface SystemPluginManagementPort {
    suspend fun discoverPlugins(): List<InstalledSTTITENIPTVPlugin>
    suspend fun providerSources(): List<ProviderSource>
    suspend fun installApkFromUrl(url: String): PluginActionResult
    suspend fun installApkFromUri(uri: Uri): PluginActionResult
    
    suspend fun setPluginEnabled(
        plugin: InstalledSTTITENIPTVPlugin,
        enabled: Boolean,
        onProgress: (String) -> Unit,
    ): PluginActionResult

    fun openPluginConfiguration(plugin: InstalledSTTITENIPTVPlugin): PluginActionResult
    
    suspend fun loadPluginConfiguration(
        plugin: InstalledSTTITENIPTVPlugin,
    ): Result<PluginConfigurationSnapshot>

    suspend fun loadPluginConfigurationValues(plugin: InstalledSTTITENIPTVPlugin): Result<JsonObject>

    suspend fun savePluginConfiguration(
        plugin: InstalledSTTITENIPTVPlugin,
        valuesJson: String,
    ): PluginActionResult

    suspend fun runPluginConfigurationAction(
        plugin: InstalledSTTITENIPTVPlugin,
        actionId: String,
    ): PluginActionResult
}
