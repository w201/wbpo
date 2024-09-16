package one.codium.wbpo.core.db.entity

import androidx.room.*
import one.codium.wbpo.core.db.entity.converters.CountryConverter

@Entity(tableName = "movie_details")
internal data class MovieDetailsEntity(
    @PrimaryKey
    val id: Long,
    val title: String,
    val imagePath: String,
    val releaseDate: String,
    val overview: String,
    val budget: Int?,
    val voteAverage: Double,
    val voteCount: Int,
    val originCountry: List<String>?,
    val revenue: Int?
)
