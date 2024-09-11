package one.codium.wbpo.feature.movies.ui.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import one.codium.wbpo.network.repo.MovieRepo
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val networkRepo: MovieRepo
): ViewModel() {

    val movieFlow = networkRepo.getPopularList().cachedIn(viewModelScope)

}
