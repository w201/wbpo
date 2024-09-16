package one.codium.wbpo.core.repo

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import one.codium.wbpo.core.db.MovieDB
import one.codium.wbpo.core.ds.fav.FavDS
import one.codium.wbpo.core.ds.settings.SettingsDataSource
import one.codium.wbpo.core.repo.fav.FavRepo
import one.codium.wbpo.core.repo.fav.FavRepoImpl
import one.codium.wbpo.core.repo.movie.MovieRepo
import one.codium.wbpo.core.repo.movie.MovieRepoImpl
import one.codium.wbpo.core.repo.settings.SettingsRepo
import one.codium.wbpo.core.repo.settings.SettingsRepoImpl
import one.codium.wbpo.network.di.NetworkModule
import one.codium.wbpo.network.repo.MovieNetworkRepo
import javax.inject.Inject
import javax.inject.Singleton

@Module(includes = [InternalRepoModule::class])
@InstallIn(SingletonComponent::class)
interface RepoModule

@Module
@InstallIn(SingletonComponent::class)
internal object InternalRepoModule {

    @Provides
    @Singleton
    fun getFavRepo(favDS: FavDS): FavRepo {
        return FavRepoImpl(favDS)
    }

    @Provides
    @Singleton
    fun getSettingsRepo(settingsDataSource: SettingsDataSource): SettingsRepo {
        return SettingsRepoImpl(settingsDataSource)
    }

    @Provides
    @Singleton
    fun getMovieRepo(movieDb: MovieDB, movieNetworkRepo: MovieNetworkRepo): MovieRepo {
        return MovieRepoImpl(movieDb, movieNetworkRepo)
    }
}
