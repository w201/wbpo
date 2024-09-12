package one.codium.wbpo.core.repo.settings

import one.codium.wbpo.core.ds.settings.SettingsDataSource
import one.codium.wbpo.core.entity.ThemeMode
import javax.inject.Inject

class SettingsRepoImpl @Inject constructor(
    private val settingsDataSource: SettingsDataSource
): SettingsRepo {
    private val themeKey = "themeMode"

    override fun setThemeMode(mode: ThemeMode) {
        settingsDataSource.setValue(themeKey, mode.name)
    }

    override fun getThemeMode(): ThemeMode {
        return ThemeMode.valueOf(settingsDataSource.getValue(themeKey) ?: ThemeMode.SYSTEM.name)
    }
}
