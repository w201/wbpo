package one.codium.wbpo.core.repo.movie

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.paging.Pager
import androidx.paging.PagingConfig
import one.codium.wbpo.core.db.MovieDB
import one.codium.wbpo.core.ds.movie.MovieDataSource
import one.codium.wbpo.core.entity.MovieDetails
import one.codium.wbpo.core.entity.mapping.MovieDetailsMapping
import one.codium.wbpo.core.utils.Event
import one.codium.wbpo.network.NetworkResult
import one.codium.wbpo.network.repo.MovieNetworkRepo
import javax.inject.Inject

class MovieRepoImpl @Inject constructor(
    private val db: MovieDB,
    private val movieNetworkRepo: MovieNetworkRepo
) : MovieRepo {

    private var movieDS: MovieDataSource? = null
    private val movieDetailsDao = db.provideMovieDetailsDao()

    private val _offlineNotice = mutableStateOf<Event<Boolean>?>(null)
    val offlineNotice: State<Event<Boolean>?> = _offlineNotice

    override fun getPopularList() = Pager(
        PagingConfig(20)
    ) {
        movieDS = MovieDataSource(movieNetworkRepo, db) { _offlineNotice.value = Event(true) }
        movieDS!!
    }.flow

    override fun getOffLineNotice(): State<Event<Boolean>?> {
        return _offlineNotice
    }

    override suspend fun getMovieDetails(id: Int): NetworkResult<MovieDetails> {
        return when (val result = movieNetworkRepo.getMovieDetails(id)) {
            is NetworkResult.Error -> {
                val dbDetails = movieDetailsDao.getMovieDetails(id)
                if (dbDetails == null) {
                    NetworkResult.Error(result.error)
                } else {
                    _offlineNotice.value = Event(true)
                    NetworkResult.Success(MovieDetailsMapping.instance.getMovieDetailsEntity(dbDetails))
                }
            }

            is NetworkResult.Success -> {
                val entity = MovieDetailsMapping.instance.getMovieDetailsEntity(result.data)
                movieDetailsDao.insert(entity)
                NetworkResult.Success(MovieDetailsMapping.instance.getMovieDetails(result.data))
            }
        }
    }

    override fun reload() {
        movieDS?.invalidate()
    }
}
