package one.codium.wbpo.network.repo

import android.content.Context
import androidx.paging.Pager
import androidx.paging.PagingConfig
import one.codium.wbpo.network.MovieAPI
import one.codium.wbpo.network.NetworkResult
import one.codium.wbpo.network.dto.movie_details.MovieDetailsMapping
import one.codium.wbpo.network.entity.MovieDetails
import retrofit2.Response

class MovieRepoImpl(
    private val movieAPI: MovieAPI,
    private val context: Context
) : MovieRepo {

   private var movieDS: MovieNetworkDataSource? = null

    override fun getPopularList() = Pager(
        PagingConfig(20)
    ) {
        movieDS = MovieNetworkDataSource(movieAPI)
        movieDS!!
    }.flow

    override suspend fun getMovieDetails(id: Int): NetworkResult<MovieDetails> {
        return safecall( suspend { movieAPI.getMovie(id) }) {
            MovieDetailsMapping.instance.getMovieDetails(it)
        }
    }

    override fun reload() {
        movieDS?.invalidate()
    }

    private suspend fun <T,R> safecall(call: suspend ()->Response<T>, mapping: (T) -> R): NetworkResult<R> {
        return try {
            val response = call()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) {
                    NetworkResult.Success(mapping.invoke(body))
                } else {
                    NetworkResult.Error(Throwable(context.getString(one.codium.wbpo.network.R.string.general_network_error)))
                }
            } else {
                NetworkResult.Error(Throwable(context.getString(one.codium.wbpo.network.R.string.general_network_error)))
            }
        } catch (e: Exception) {
            e.printStackTrace()
            NetworkResult.Error(e)
        }
    }

}
