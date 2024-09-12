package one.codium.wbpo.feature.info.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import one.codium.wbpo.feature.info.R
import java.util.*

@Composable
fun InfoScreen(viewModel: InfoScreenViewModel = hiltViewModel()) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        val info = viewModel.uiState.value.systemInfo
        Parameter(stringResource(R.string.info_model, info.model))
        Parameter(stringResource(R.string.info_brand, info.brand))
        Parameter(stringResource(R.string.info_manufacturer, info.brand))
        Parameter(stringResource(R.string.info_board, info.board))
        Parameter(stringResource(R.string.info_version, info.version))
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            stringResource(R.string.info_copyright, info.developer, Calendar.getInstance()[Calendar.YEAR]),
            style = MaterialTheme.typography.bodySmall
        )
    }
}

@Composable
fun Parameter(string: String) {
    Text(text = string)
}
