package one.codium.wbpo.core.ds.movie

import androidx.paging.PagingSource
import androidx.paging.PagingState
import androidx.room.withTransaction
import one.codium.wbpo.core.db.MovieDB
import one.codium.wbpo.core.db.dao.MovieDao
import one.codium.wbpo.core.entity.Movie
import one.codium.wbpo.core.entity.mapping.MovieMapping
import one.codium.wbpo.network.NetworkResult
import one.codium.wbpo.network.dto.movie.MovieDTO
import one.codium.wbpo.network.repo.MovieNetworkRepo

internal class MovieDataSource(
    private val movieNetworkRepo: MovieNetworkRepo,
    private val db: MovieDB,
    private val offlineNotice: () -> Unit
) : PagingSource<Int, Movie>() {

    private val movieDao: MovieDao = db.provideDao()

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? =
        state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
        }


    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        val page = params.key ?: 1
        return try {
            val response = movieNetworkRepo.getMovieByPage(page)
            if (response is NetworkResult.Success) {
                val data = response.data
                val nextKey = if (data.results.isEmpty()) null else page + 1
                val prevKey = if (page == 1) null else page - 1
                saveToDB(data.results, page)
                LoadResult.Page(MovieMapping.instance.getMovies(data.results), prevKey, nextKey)
            } else {
                returnFromDBOrError(page, Throwable((response as NetworkResult.Error).error))
            }
        } catch (e: Exception) {
            returnFromDBOrError(page, e)
        }
    }

    private suspend fun returnFromDBOrError(page: Int, e: Throwable): PagingSource.LoadResult<Int, Movie> {
        val result = movieDao.movieListByPage(page)
        return if (result.isEmpty()) {
            LoadResult.Error(e)
        } else {
            val nextKey = page + 1
            val prevKey = if (page == 1) null else page - 1
            offlineNotice.invoke()
            LoadResult.Page(MovieMapping.instance.toMovie(result), prevKey, nextKey)
        }
    }

    private suspend fun saveToDB(results: List<MovieDTO>, page: Int) {
        db.withTransaction {
            movieDao.deleteByPage(page)
            val list = MovieMapping.instance.toMovieEntity(results, page)
            movieDao.insertAll(list)
        }
    }

}
