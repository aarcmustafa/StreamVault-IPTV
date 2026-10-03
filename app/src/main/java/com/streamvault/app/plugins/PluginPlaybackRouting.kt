package com.streamvault.app.plugins

import com.streamvault.feature.system.api.InstalledSTTITENIPTVPlugin
import com.streamvault.feature.system.api.STTITENIPTVPluginManifest
import java.net.URI
import kotlinx.coroutines.withTimeoutOrNull

internal suspend fun <T> withPluginPlaybackDeadline(
    timeoutMillis: Long,
    block: suspend () -> T
): T? = withTimeoutOrNull(timeoutMillis) { block() }

/**
 * Selects only plugins that explicitly own the URL.  Empty routing metadata is
 * deliberately not a wildcard: old generic handlers must not capture traffic.
 */
internal fun playbackCandidates(
    plugins: List<InstalledSTTITENIPTVPlugin>,
    url: String,
    capability: String
): List<InstalledSTTITENIPTVPlugin> {
    val parsed = runCatching { URI(url) }.getOrNull() ?: return emptyList()
    val scheme = parsed.scheme?.lowercase() ?: return emptyList()
    val host = parsed.host?.lowercase()
    return plugins.asSequence()
        .filter { it.enabled && it.manifest.hasCapability(capability) }
        .filter { plugin -> plugin.manifest.ownsPlaybackUrl(scheme, host) }
        .sortedWith(
            compareByDescending<InstalledSTTITENIPTVPlugin> { it.manifest.playbackPriority }
                .thenBy { it.packageName }
                .thenBy { it.serviceClassName }
                .thenBy { it.manifest.id }
        )
        .toList()
}

private fun STTITENIPTVPluginManifest.ownsPlaybackUrl(scheme: String, host: String?): Boolean {
    val schemeOwned = playbackUrlSchemes.any { it.equals("*", true) || it.equals(scheme, true) }
    if (!schemeOwned) return false
    // Hostless schemes (for example, a custom URI) can be owned by scheme alone.
    return host == null || playbackUrlHosts.any { it.equals("*", true) || it.equals(host, true) }
}
