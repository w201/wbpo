package one.codium.wbpo.core.repo.fav

interface FavRepo {
    fun isFav(movieId: Int): Boolean
    fun toggleFav(movieId: Int)
}
