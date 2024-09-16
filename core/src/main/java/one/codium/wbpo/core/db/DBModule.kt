package one.codium.wbpo.core.db

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import one.codium.wbpo.core.db.dao.MovieDao
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object DBModule {

    @Provides
    @Singleton
    fun getDBMovie(@ApplicationContext context: Context): MovieDB {
        return Room.databaseBuilder(
            context,
            MovieDB::class.java,
            "wbpo.db"
        ).build()
    }

    @Provides
    @Singleton
    fun getMovieDao(movieDB: MovieDB): MovieDao {
        return movieDB.provideDao()
    }

}
