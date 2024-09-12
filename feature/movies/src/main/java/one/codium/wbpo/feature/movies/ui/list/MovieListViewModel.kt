package one.codium.wbpo.feature.movies.ui.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.map
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import one.codium.wbpo.core.repo.fav.FavRepo
import one.codium.wbpo.network.entity.Movie
import one.codium.wbpo.network.repo.MovieRepo
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val networkRepo: MovieRepo,
    private val favRepo: FavRepo
) : ViewModel() {
    fun toggleFav(movie: Movie) {
        favRepo.toggleFav(movie.id)
        movie.isFavorite = !movie.isFavorite
        networkRepo.reload()
    }

    val movieFlow = networkRepo.getPopularList()
        .map { it.map { it.copy(isFavorite = favRepo.isFav(it.id)) } }
        .cachedIn(viewModelScope)

}
