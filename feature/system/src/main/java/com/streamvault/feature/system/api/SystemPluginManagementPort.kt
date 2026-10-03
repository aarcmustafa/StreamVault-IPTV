package com.streamvault.feature.system.api

import kotlinx.serialization.json.JsonObject

interface SystemPluginManagementPort {
    suspend fun discoverPlugins(): List<InstalledSTTITENIPTVPlugin>
    
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
