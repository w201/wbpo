package one.codium.wbpo.settings.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import one.codium.wbpo.core.entity.ThemeMode

@Composable
fun SettingsScreen(viewModel: SettingsViewModel = hiltViewModel(), onThemeSelected: (ThemeMode) -> Unit) {

    ShowThemeMode(viewModel.uiState.value) {
        viewModel.selectedTheme(it)
        onThemeSelected(it)
    }
}

@Composable
fun ShowThemeMode(state: SettingsUIState, onSelected: (ThemeMode) -> Unit) {
    Column {
        state.listOfOptions.forEach { option ->
            Row(modifier = Modifier
                .fillMaxWidth()
                .selectable(option.value == state.themeMode, onClick = {onSelected(option.value)}),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(selected = option.value == state.themeMode, onClick = {onSelected(option.value)})
                Spacer(modifier = Modifier.width(4.dp))
                Text(text = stringResource(option.titleId))
            }
        }
    }
}
