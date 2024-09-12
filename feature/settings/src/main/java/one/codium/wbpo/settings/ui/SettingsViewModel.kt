package one.codium.wbpo.settings.ui

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import one.codium.wbpo.core.entity.ThemeMode
import one.codium.wbpo.core.repo.settings.SettingsRepo
import one.codium.wbpo.settings.R
import one.codium.wbpo.settings.entity.SettingsItem
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val settingsRepo: SettingsRepo
) : ViewModel() {

    private val _uiState = mutableStateOf(SettingsUIState(settingsRepo.getThemeMode(), getSettingItems()))

    private fun getSettingItems()  =  listOf(
        SettingsItem(R.string.settings_lightMode, ThemeMode.LIGHT),
        SettingsItem(R.string.settings_darkMode, ThemeMode.DARK),
        SettingsItem(R.string.settings_systemMode, ThemeMode.SYSTEM)
    )

    fun selectedTheme(mode: ThemeMode) {
        settingsRepo.setThemeMode(mode)
        _uiState.value = _uiState.value.copy(themeMode = mode)
    }

    val uiState: State<SettingsUIState> = _uiState

}

data class SettingsUIState(
    val themeMode: ThemeMode = ThemeMode.SYSTEM,
    val listOfOptions: List<SettingsItem>
)
