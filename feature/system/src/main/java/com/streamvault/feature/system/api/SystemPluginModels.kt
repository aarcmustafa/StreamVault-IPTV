package com.streamvault.feature.system.api

import kotlinx.serialization.Serializable

@Serializable
data class STTITENIPTVPluginManifest(
    val schemaVersion: Int = 1,
    val id: String,
    val name: String,
    val version: String = "",
    val versionCode: Int = 1,
    val description: String = "",
    val author: String = "",
    val configurationMode: String = STTITENIPTVPluginContract.CONFIGURATION_MODE_NONE,
    val configurationActivityAction: String? = null,
    val configurationActivityComponent: String? = null,
    val capabilities: List<String> = emptyList(),
    val permissions: List<String> = emptyList()
) {
    fun hasCapability(capability: String): Boolean = capability in capabilities

    val supportsConfigurationActivity: Boolean
        get() = hasCapability(STTITENIPTVPluginContract.CAPABILITY_CONFIGURATION_ACTIVITY) &&
            !configurationActivityAction.isNullOrBlank()

    val usesActivityConfiguration: Boolean
        get() = configurationMode == STTITENIPTVPluginContract.CONFIGURATION_MODE_ACTIVITY

    val supportsHostRenderedConfiguration: Boolean
        get() = configurationMode == STTITENIPTVPluginContract.CONFIGURATION_MODE_HOST_SCHEMA ||
            (configurationMode != STTITENIPTVPluginContract.CONFIGURATION_MODE_ACTIVITY &&
                hasCapability(STTITENIPTVPluginContract.CAPABILITY_CONFIGURATION_SCHEMA))

    val canConfigure: Boolean
        get() = supportsHostRenderedConfiguration || supportsConfigurationActivity
}

data class InstalledSTTITENIPTVPlugin(
    val packageName: String,
    val serviceClassName: String,
    val appLabel: String,
    val manifest: STTITENIPTVPluginManifest,
    val enabled: Boolean,
    val statusLabel: String = "",
    val lastMessage: String = "",
    val discoveryState: PluginDiscoveryState = PluginDiscoveryState.READY
) {
    val displayName: String
        get() = manifest.name.ifBlank { appLabel.ifBlank { packageName } }
}

data class STTITENIPTVPluginOwner(
    val packageName: String,
    val serviceClassName: String,
    val manifestId: String
) {
    val component: STTITENIPTVPluginComponent
        get() = STTITENIPTVPluginComponent(packageName, serviceClassName)
}

fun STTITENIPTVPluginOwner.toBundleSafeKey(): String = buildString {
    appendLengthPrefixed(packageName)
    appendLengthPrefixed(serviceClassName)
    appendLengthPrefixed(manifestId)
}

data class STTITENIPTVPluginComponent(
    val packageName: String,
    val serviceClassName: String
)

val InstalledSTTITENIPTVPlugin.owner: STTITENIPTVPluginOwner
    get() = STTITENIPTVPluginOwner(packageName, serviceClassName, manifest.id)

enum class PluginDiscoveryState {
    READY,
    UPDATING,
    ERROR
}

private fun StringBuilder.appendLengthPrefixed(value: String) {
    append(value.length)
    append(':')
    append(value)
}
