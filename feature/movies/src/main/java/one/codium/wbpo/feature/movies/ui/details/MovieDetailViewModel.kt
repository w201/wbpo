package one.codium.wbpo.feature.movies.ui.details

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import one.codium.wbpo.core.repo.fav.FavRepo
import one.codium.wbpo.network.NetworkResult
import one.codium.wbpo.network.entity.MovieDetails
import one.codium.wbpo.network.repo.MovieRepo
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val movieRepo: MovieRepo,
    private val savedStateHandle: SavedStateHandle,
    private val favRepo: FavRepo
) : ViewModel() {

    private val _uiState = mutableStateOf(MovieDetailState(true))
    val uiState: State<MovieDetailState> = _uiState

    private val movieId = savedStateHandle.get<Int>("id")

    init {
        viewModelScope.launch {
            if (movieId != null) {
                when (val response = movieRepo.getMovieDetails(movieId)) {
                    is NetworkResult.Success -> {
                        _uiState.value = MovieDetailState(movie = response.data.copy(isFavorite = favRepo.isFav(movieId)))
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

    fun toggleFav() {
        movieId?.let {
            favRepo.toggleFav(it)
            _uiState.value = _uiState.value.copy(movie = _uiState.value.movie?.copy(isFavorite = favRepo.isFav(it)))
            movieRepo.reload()
        }
    }

}

data class MovieDetailState(
    val inProgress: Boolean = false,
    val movie: MovieDetails? = null,
    val errorMessage: String? = null
)
