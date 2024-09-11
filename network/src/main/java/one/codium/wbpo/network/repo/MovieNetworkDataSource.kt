package one.codium.wbpo.network.repo

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import one.codium.wbpo.network.MovieAPI
import one.codium.wbpo.network.dto.movie.MovieMapping
import one.codium.wbpo.network.entity.Movie

class MovieNetworkDataSource(
    private val api: MovieAPI
) : PagingSource<Int, Movie>() {

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        val ret =  state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
        }
        return ret
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        val page = params.key ?: 1
        return try {
            Log.v("w201", "page: $page")
            val response = api.getPopularMovies(page)
            val body = response.body()
            if (response.isSuccessful && body != null) {
                val nextKey = if(body.results.isEmpty()) null else page+1
                val prevKey = if (page == 1) null else page - 1
                Log.v("w201", "nextKey: $nextKey, prevKey: $prevKey")
                LoadResult.Page(MovieMapping.instance.getMovies(body.results), prevKey, nextKey)
            } else {
                LoadResult.Error(Throwable(response.message()))
            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}
