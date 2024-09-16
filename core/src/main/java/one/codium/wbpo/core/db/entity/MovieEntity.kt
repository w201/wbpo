package one.codium.wbpo.core.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie")
internal data class MovieEntity(
    @PrimaryKey
    val id: Int,
    val page: Int,
    val backdropPath: String?,
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    val posterPath: String,
    val releaseDate: String,
    val title: String,
    val voteAverage: Double,
    val voteCount: Int
)
