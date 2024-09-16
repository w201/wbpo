package one.codium.wbpo.core.repo.movie

import androidx.compose.runtime.State
import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import one.codium.wbpo.core.entity.Movie
import one.codium.wbpo.core.entity.MovieDetails
import one.codium.wbpo.core.utils.Event
import one.codium.wbpo.network.NetworkResult
import one.codium.wbpo.network.dto.movie.MovieDTO

interface MovieRepo {
    fun reload()
    fun getPopularList(): Flow<PagingData<Movie>>
    fun getOffLineNotice(): State<Event<Boolean>?>
    suspend fun getMovieDetails(id: Int): NetworkResult<MovieDetails>
}
