package one.codium.wbpo.core.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import one.codium.wbpo.core.db.dao.MovieDao
import one.codium.wbpo.core.db.dao.MovieDetailsDao
import one.codium.wbpo.core.db.entity.MovieDetailsEntity
import one.codium.wbpo.core.db.entity.MovieEntity
import one.codium.wbpo.core.db.entity.converters.CountryConverter

@Database(
    entities = [
        MovieEntity::class,
        MovieDetailsEntity::class
    ],
    version = 1
)
@TypeConverters(CountryConverter::class)
abstract class MovieDB : RoomDatabase() {
    abstract fun provideDao(): MovieDao
    abstract fun provideMovieDetailsDao(): MovieDetailsDao
}
