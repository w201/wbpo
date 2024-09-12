package one.codium.wbpo.core.repo.fav

import one.codium.wbpo.core.ds.fav.FavDS
import javax.inject.Inject

class FavRepoImpl @Inject constructor(private val favDS: FavDS) : FavRepo {
    override fun isFav(movieId: Int): Boolean {
        return favDS.getFavMoviesId().contains(movieId)
    }

    override fun toggleFav(movieId: Int) {
        if (isFav(movieId)) {
            favDS.removeFavMovie(movieId)
        } else {
            favDS.setFavMovie(movieId)
        }
    }

}
