package one.codium.wbpo.feature.movies.ui.details

import android.app.Application
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.*
import androidx.lifecycle.ViewModelProvider.*
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import one.codium.wbpo.network.NetworkResult
import one.codium.wbpo.network.entity.MovieDetails
import one.codium.wbpo.network.repo.MovieRepo
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val movieRepo: MovieRepo,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _uiState = mutableStateOf(MovieDetailState(true))
    val uiState: State<MovieDetailState> = _uiState

    init {
        viewModelScope.launch {
            val movieId = savedStateHandle.get<Int>("id")
            if (movieId != null) {
                when (val response = movieRepo.getMovieDetails(movieId)) {
                    is NetworkResult.Success -> {
                        _uiState.value = MovieDetailState(movie = response.data)
                    }

                    is NetworkResult.Error -> {
                        _uiState.value = MovieDetailState(errorMessage = response.error.message)
                    }
                }
            } else {
                _uiState.value = MovieDetailState(false, errorMessage = "Can't find the movie id")
            }
        }
    }
}

data class MovieDetailState(
    val inProgress: Boolean = false,
    val movie: MovieDetails? = null,
    val errorMessage: String? = null
)
