package one.codium.wbpo.network.repo

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import one.codium.wbpo.network.NetworkResult
import one.codium.wbpo.network.dto.movie.MovieDTO
import one.codium.wbpo.network.dto.movie.MovieListDTO
import one.codium.wbpo.network.dto.movie_details.MovieDetailsDTO

interface MovieNetworkRepo {

    suspend fun getMovieDetails(id: Int): NetworkResult<MovieDetailsDTO>
    suspend fun getMovieByPage(page: Int): NetworkResult<MovieListDTO>

}
