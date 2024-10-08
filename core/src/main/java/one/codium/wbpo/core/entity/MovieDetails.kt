package one.codium.wbpo.core.entity

data class MovieDetails(
    val id: Long,
    val title: String,
    val imagePath: String,
    val releaseDate: String,
    val overview: String,
    val budget: Int?,
    val voteAverage: Double,
    val voteCount: Int,
    val originCountry: List<String>?,
    val revenue: Int?,
    var isFavorite: Boolean = false

) {
    val image = "https://image.tmdb.org/t/p/w500/$imagePath"
}


