package one.codium.wbpo.core.ds.fav

interface FavDS {
    fun getFavMoviesId(): Set<Int>
    fun setFavMovie(id: Int)
    fun removeFavMovie(id: Int)
}
