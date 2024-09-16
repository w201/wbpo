package one.codium.wbpo.core.db.dao

import androidx.room.*
import one.codium.wbpo.core.db.entity.MovieDetailsEntity
import one.codium.wbpo.core.db.entity.MovieEntity

@Dao
interface MovieDetailsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(movies: MovieDetailsEntity)

    @Query("SELECT * from movie_details WHERE id = :id")
    suspend fun getMovieDetails(id: Int): MovieDetailsEntity?

}
