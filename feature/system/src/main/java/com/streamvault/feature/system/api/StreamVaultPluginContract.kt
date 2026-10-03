package com.streamvault.feature.system.api

object STTITENIPTVPluginContract {
    const val API_VERSION = 1

    const val INTENT_ACTION_PLUGIN_SERVICE = "com.streamvault.plugin.action.PLUGIN_SERVICE"
    const val METADATA_KEY_MANIFEST = "com.streamvault.plugin.manifest"

    const val CAPABILITY_CONFIGURATION_ACTIVITY = "configuration_activity"
    const val CAPABILITY_CONFIGURATION_SCHEMA = "configuration_schema"
    const val CAPABILITY_EPG_PROVIDER = "epg_provider"
    const val CAPABILITY_STREAM_PROVIDER = "stream_provider"

    const val CONFIGURATION_MODE_NONE = "none"
    const val CONFIGURATION_MODE_ACTIVITY = "activity"
    const val CONFIGURATION_MODE_HOST_SCHEMA = "host_schema"

    const val ACTION_CONFIGURE = "com.streamvault.plugin.action.CONFIGURE"
}
