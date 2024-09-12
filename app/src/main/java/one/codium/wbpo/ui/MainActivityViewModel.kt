package one.codium.wbpo.ui

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import one.codium.wbpo.core.entity.ThemeMode
import one.codium.wbpo.core.repo.settings.SettingsRepo
import one.codium.wbpo.entity.NavigationItem
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val settingsRepo: SettingsRepo
) : ViewModel() {

    private val _uiState = mutableStateOf(MainUiState(settingsRepo.getThemeMode()))
    val uiState: State<MainUiState> = _uiState

    val drawerMenu = listOf(
        NavigationItem.MOVIES,
        NavigationItem.SETTINGS,
        NavigationItem.INFO
    )

}

data class MainUiState(
    val themeMode: ThemeMode = ThemeMode.SYSTEM
)
