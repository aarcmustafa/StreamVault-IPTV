package com.streamvault.app.system

import android.net.Uri
import com.streamvault.app.plugins.STTITEN IP TVPluginManager
import com.streamvault.domain.model.Result
import com.streamvault.domain.provider.ProviderSource
import com.streamvault.domain.provider.ProviderSourceRegistry
import com.streamvault.feature.system.api.InstalledSTTITEN IP TVPlugin
import com.streamvault.feature.system.api.PluginActionResult
import com.streamvault.feature.system.api.PluginConfigurationSnapshot
import com.streamvault.feature.system.api.SystemPluginManagementPort
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.serialization.json.JsonObject

@Singleton
class AppSystemPluginManagementAdapter @Inject constructor(
    private val pluginManager: STTITEN IP TVPluginManager,
    private val providerSourceRegistry: ProviderSourceRegistry,
) : SystemPluginManagementPort {
    override suspend fun discoverPlugins(): List<InstalledSTTITEN IP TVPlugin> =
        pluginManager.discoverPlugins()

    override suspend fun providerSources(): List<ProviderSource> = providerSourceRegistry.sources()

    override suspend fun installApkFromUri(uri: Uri): Result<Unit> = pluginManager.installApkFromUri(uri)

    override suspend fun installApkFromUrl(url: String): Result<Unit> = pluginManager.installApkFromUrl(url)

    override suspend fun setPluginEnabled(
        plugin: InstalledSTTITEN IP TVPlugin,
        enabled: Boolean,
        onProgress: (String) -> Unit,
    ): PluginActionResult = pluginManager.setPluginEnabled(plugin, enabled, onProgress)

    override fun openPluginConfiguration(plugin: InstalledSTTITEN IP TVPlugin): PluginActionResult =
        pluginManager.openPluginConfiguration(plugin)

    override suspend fun loadPluginConfiguration(
        plugin: InstalledSTTITEN IP TVPlugin,
    ): Result<PluginConfigurationSnapshot> = pluginManager.loadPluginConfiguration(plugin)

    override suspend fun loadPluginConfigurationValues(plugin: InstalledSTTITEN IP TVPlugin): Result<JsonObject> =
        pluginManager.loadPluginConfigurationValues(plugin)

    override suspend fun savePluginConfiguration(
        plugin: InstalledSTTITEN IP TVPlugin,
        valuesJson: String,
    ): PluginActionResult = pluginManager.savePluginConfiguration(plugin, valuesJson)

    override suspend fun runPluginConfigurationAction(
        plugin: InstalledSTTITEN IP TVPlugin,
        actionId: String,
    ): PluginActionResult = pluginManager.runPluginConfigurationAction(plugin, actionId)
}
