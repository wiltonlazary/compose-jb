/*
 * Copyright 2020-2021 JetBrains s.r.o. and respective authors and developers.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE.txt file.
 */

package org.jetbrains.compose.desktop.application.internal.validation

import org.gradle.api.provider.Provider
import org.jetbrains.compose.desktop.application.dsl.MacOSNotarizationSettings
import org.jetbrains.compose.desktop.application.internal.ComposeProperties

internal data class ValidatedMacOSNotarizationSettings(
    val bundleID: String,
    val appleID: String,
    val password: String,
    val ascProvider: String?
)

internal fun MacOSNotarizationSettings?.validate(
    bundleIDProvider: Provider<String?>
): ValidatedMacOSNotarizationSettings {
    checkNotNull(this) {
        ERR_NOTARIZATION_SETTINGS_ARE_NOT_PROVIDED
    }

    val bundleID = validateBundleID(bundleIDProvider)
    check(!appleID.orNull.isNullOrEmpty()) {
        ERR_APPLE_ID_IS_EMPTY
    }
    check(!password.orNull.isNullOrEmpty()) {
        ERR_PASSWORD_IS_EMPTY
    }
    return ValidatedMacOSNotarizationSettings(
        bundleID = bundleID,
        appleID = appleID.orNull!!,
        password = password.orNull!!,
        ascProvider = ascProvider.orNull
    )
}

private const val ERR_PREFIX = "Notarization settings error:"
private const val ERR_NOTARIZATION_SETTINGS_ARE_NOT_PROVIDED =
    "$ERR_PREFIX notarization settings are not provided"
private val ERR_APPLE_ID_IS_EMPTY =
    """|$ERR_PREFIX appleID is null or empty. To specify:
               |  * Use '${ComposeProperties.MAC_NOTARIZATION_APPLE_ID}' Gradle property;
               |  * Or use 'nativeDistributions.macOS.notarization.appleID' DSL property;
            """.trimMargin()
private val ERR_PASSWORD_IS_EMPTY =
    """|$ERR_PREFIX password is null or empty. To specify:
               |  * Use '${ComposeProperties.MAC_NOTARIZATION_PASSWORD}' Gradle property;
               |  * Or use 'nativeDistributions.macOS.notarization.password' DSL property;
            """.trimMargin()