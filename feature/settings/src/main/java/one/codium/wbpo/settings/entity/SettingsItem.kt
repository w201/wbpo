package one.codium.wbpo.settings.entity

import androidx.annotation.StringRes
import one.codium.wbpo.core.entity.ThemeMode

data class SettingsItem(
    @StringRes val titleId: Int,
    val value: ThemeMode
)
