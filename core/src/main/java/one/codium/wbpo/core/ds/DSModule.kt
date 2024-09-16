package one.codium.wbpo.core.ds

import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import one.codium.wbpo.core.ds.fav.FavDS
import one.codium.wbpo.core.ds.fav.FavDSImpl
import one.codium.wbpo.core.ds.movie.MovieDataSource
import one.codium.wbpo.core.ds.settings.SettingsDataSource
import one.codium.wbpo.core.ds.settings.SettingsDataSourceImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DSModule {

    @Provides
    @Singleton
    fun getFavDS(prefs: SharedPreferences): FavDS {
        return FavDSImpl(prefs)
    }

    @Provides
    @Singleton
    fun getSettingsDS(prefs: SharedPreferences): SettingsDataSource {
        return SettingsDataSourceImpl(prefs)
    }

}
