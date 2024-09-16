package one.codium.wbpo.core.db.dao

import androidx.room.*
import one.codium.wbpo.core.db.entity.MovieEntity

@Dao
internal interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(movies: List<MovieEntity>)

    @Query("SELECT * FROM movie WHERE page = :page")
    suspend fun movieListByPage(page: Int): List<MovieEntity>

    @Query("DELETE FROM movie WHERE page = :page")
    suspend fun deleteByPage(page: Int)

    @Query("DELETE FROM movie")
    suspend fun clearAll()
}
