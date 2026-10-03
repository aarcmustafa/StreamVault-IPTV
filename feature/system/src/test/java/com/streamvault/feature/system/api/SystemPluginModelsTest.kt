package com.streamvault.feature.system.api

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class SystemPluginModelsTest {
    @Test
    fun pluginOwnerKeyIsBundleSafeAndUnambiguous() {
        val left = STTITEN IP TVPluginOwner("ab", "c", "d").toBundleSafeKey()
        val right = STTITEN IP TVPluginOwner("a", "bc", "d").toBundleSafeKey()

        assertThat(left).isNotEqualTo(right)
        assertThat(left).isEqualTo("2:ab1:c1:d")
    }

    @Test
    fun manifestConfigurationCapabilitiesPreserveCurrentRules() {
        val activityManifest = STTITEN IP TVPluginManifest(
            id = "activity",
            name = "Activity",
            capabilities = listOf(STTITEN IP TVPluginContract.CAPABILITY_CONFIGURATION_ACTIVITY),
            configurationActivityAction = "com.example.CONFIGURE",
            configurationMode = STTITEN IP TVPluginContract.CONFIGURATION_MODE_ACTIVITY,
        )
        val hostManifest = STTITEN IP TVPluginManifest(
            id = "host",
            name = "Host",
            capabilities = listOf(STTITEN IP TVPluginContract.CAPABILITY_CONFIGURATION_SCHEMA),
        )

        assertThat(activityManifest.supportsConfigurationActivity).isTrue()
        assertThat(activityManifest.supportsHostRenderedConfiguration).isFalse()
        assertThat(activityManifest.canConfigure).isTrue()
        assertThat(hostManifest.supportsConfigurationActivity).isFalse()
        assertThat(hostManifest.supportsHostRenderedConfiguration).isTrue()
        assertThat(hostManifest.canConfigure).isTrue()
    }
}
