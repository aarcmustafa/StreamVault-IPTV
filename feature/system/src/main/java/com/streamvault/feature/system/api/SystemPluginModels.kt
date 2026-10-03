package com.streamvault.feature.system.api

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject

@Serializable
data class PluginConfigurationAction(
    val id: String,
    val label: String,
    val description: String = "",
    val confirmation: String? = null
)

@Serializable
data class PluginConfigurationField(
    val key: String,
    val label: String,
    val type: String = TYPE_TEXT,
    val description: String = "",
    val placeholder: String = "",
    val value: String = "",
    val readOnly: Boolean = false,
    val secret: Boolean = false,
    val options: List<PluginConfigurationOption> = emptyList()
) {
    companion object {
        const val TYPE_TEXT = "text"
        const val TYPE_TEXTAREA = "textarea"
        const val TYPE_NUMBER = "number"
        const val TYPE_BOOLEAN = "boolean"
        const val TYPE_SELECT = "select"
        const val TYPE_PASSWORD = "password"
        const val TYPE_URL = "url"
        const val TYPE_INFO = "info"
    }
}

@Serializable
data class PluginConfigurationOption(
    val label: String,
    val value: String
)

@Serializable
data class PluginConfigurationSection(
    val id: String,
    val title: String,
    val description: String = "",
    val fields: List<PluginConfigurationField> = emptyList()
)

@Serializable
data class PluginConfigurationSchema(
    val title: String = "",
    val description: String = "",
    val sections: List<PluginConfigurationSection> = emptyList(),
    val actions: List<PluginConfigurationAction> = emptyList()
)

data class ActivePluginConfiguration(
    val plugin: InstalledSTTITENIPTVPlugin,
    val schema: PluginConfigurationSchema,
    val draftValues: Map<String, String> = emptyMap(),
    val validationErrors: Map<String, String> = emptyMap(),
    val isDirty: Boolean = false,
    val isSaving: Boolean = false,
    val runningActionId: String? = null
)
