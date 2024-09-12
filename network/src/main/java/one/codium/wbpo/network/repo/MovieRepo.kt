package one.codium.wbpo.network.repo

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import one.codium.wbpo.network.NetworkResult
import one.codium.wbpo.network.entity.Movie
import one.codium.wbpo.network.entity.MovieDetails

interface MovieRepo {

    fun getPopularList(): Flow<PagingData<Movie>>
    suspend fun getMovieDetails(id: Int): NetworkResult<MovieDetails>
    fun reload()

}
