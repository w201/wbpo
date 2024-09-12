package one.codium.wbpo.core.repo

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import one.codium.wbpo.core.ds.fav.FavDS
import one.codium.wbpo.core.ds.settings.SettingsDataSource
import one.codium.wbpo.core.repo.fav.FavRepo
import one.codium.wbpo.core.repo.fav.FavRepoImpl
import one.codium.wbpo.core.repo.settings.SettingsRepo
import one.codium.wbpo.core.repo.settings.SettingsRepoImpl
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RepoModule {

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

}
