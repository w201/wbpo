package one.codium.wbpo.network.repo

import android.content.Context
import one.codium.wbpo.network.MovieAPI
import one.codium.wbpo.network.NetworkResult
import one.codium.wbpo.network.data.InternalErrorCode
import one.codium.wbpo.network.dto.movie.MovieListDTO
import one.codium.wbpo.network.dto.movie_details.MovieDetailsDTO
import retrofit2.Response

internal class MovieNetworkRepoImpl(
    private val movieAPI: MovieAPI
) : MovieNetworkRepo {

    override suspend fun getMovieDetails(id: Int): NetworkResult<MovieDetailsDTO> {
        return safecall(suspend { movieAPI.getMovie(id) }) { it }
    }

    override suspend fun getMovieByPage(page: Int): NetworkResult<MovieListDTO> {
        return safecall({ movieAPI.getPopularMovies(page) }, { it })
    }

    private suspend fun <T, R> safecall(call: suspend () -> Response<T>, mapping: (T) -> R): NetworkResult<R> {
        return try {
            val response = call()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) {
                    NetworkResult.Success(mapping.invoke(body))
                } else {
                    NetworkResult.Error(internalCode = InternalErrorCode.GENERAL_ERROR)
                }
            } else {
                NetworkResult.Error(internalCode = InternalErrorCode.GENERAL_ERROR)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            NetworkResult.Error(e)
        }
    }

}
