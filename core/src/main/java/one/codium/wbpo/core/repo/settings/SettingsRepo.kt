package one.codium.wbpo.core.repo.settings

import one.codium.wbpo.core.entity.ThemeMode

interface SettingsRepo {

    fun setThemeMode(mode: ThemeMode)
    fun getThemeMode(): ThemeMode

}
