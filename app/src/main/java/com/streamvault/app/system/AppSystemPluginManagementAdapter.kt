package com.streamvault.app.system

import android.net.Uri
import com.streamvault.app.plugins.STTITENIPTVPluginManager
import com.streamvault.domain.model.Result
import com.streamvault.domain.provider.ProviderSource
import com.streamvault.domain.provider.ProviderSourceRegistry
import com.streamvault.feature.system.api.InstalledSTTITENIPTVPlugin
import com.streamvault.feature.system.api.PluginActionResult
import com.streamvault.feature.system.api.PluginConfigurationSnapshot
import com.streamvault.feature.system.api.SystemPluginManagementPort
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.serialization.json.JsonObject

@Singleton
class AppSystemPluginManagementAdapter @Inject constructor(
    private val pluginManager: STTITENIPTVPluginManager,
    private val providerSourceRegistry: ProviderSourceRegistry,
) : SystemPluginManagementPort {
    override suspend fun discoverPlugins(): List<InstalledSTTITENIPTVPlugin> =
        pluginManager.discoverPlugins()

    override suspend fun providerSources(): List<ProviderSource> = providerSourceRegistry.sources()

    override suspend fun installApkFromUri(uri: Uri): Result<Unit> = pluginManager.installApkFromUri(uri)

    override suspend fun installApkFromUrl(url: String): Result<Unit> = pluginManager.installApkFromUrl(url)

    override suspend fun setPluginEnabled(
        plugin: InstalledSTTITENIPTVPlugin,
        enabled: Boolean,
        onProgress: (String) -> Unit,
    ): PluginActionResult = pluginManager.setPluginEnabled(plugin, enabled, onProgress)

    override fun openPluginConfiguration(plugin: InstalledSTTITENIPTVPlugin): PluginActionResult =
        pluginManager.openPluginConfiguration(plugin)

    override suspend fun loadPluginConfiguration(
        plugin: InstalledSTTITENIPTVPlugin,
    ): Result<PluginConfigurationSnapshot> = pluginManager.loadPluginConfiguration(plugin)

    override suspend fun loadPluginConfigurationValues(plugin: InstalledSTTITENIPTVPlugin): Result<JsonObject> =
        pluginManager.loadPluginConfigurationValues(plugin)

    override suspend fun savePluginConfiguration(
        plugin: InstalledSTTITENIPTVPlugin,
        valuesJson: String,
    ): PluginActionResult = pluginManager.savePluginConfiguration(plugin, valuesJson)

    override suspend fun runPluginConfigurationAction(
        plugin: InstalledSTTITENIPTVPlugin,
        actionId: String,
    ): PluginActionResult = pluginManager.runPluginConfigurationAction(plugin, actionId)
}
