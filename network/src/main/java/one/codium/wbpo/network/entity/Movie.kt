package one.codium.wbpo.network.entity

data class Movie(
    val backdropPath: String?,
    val id: Int,
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    val posterPath: String,
    val releaseDate: String,
    val title: String,
    val voteAverage: Double,
    val voteCount: Int,
    var isFavorite: Boolean = false,
)

fun Movie.getImagePath() = "https://image.tmdb.org/t/p/w300$posterPath"
