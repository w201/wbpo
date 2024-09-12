package one.codium.wbpo.feature.info.ui

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import one.codium.wbpo.feature.info.entity.SystemInfo
import one.codium.wbpo.feature.info.usecase.InfoUsecase
import javax.inject.Inject

@HiltViewModel
class InfoScreenViewModel @Inject constructor(
    private val getInfoUseCase: InfoUsecase
): ViewModel() {

    private val _uiState = mutableStateOf(InfoScreenState(getInfoUseCase()))
    val uiState: State<InfoScreenState> = _uiState

}

data class InfoScreenState(
    val systemInfo: SystemInfo
)
