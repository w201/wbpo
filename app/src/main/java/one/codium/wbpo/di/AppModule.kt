package one.codium.wbpo.di

import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun getPrefs(@ApplicationContext context: Context): SharedPreferences =
        context.getSharedPreferences("wbpo", Context.MODE_PRIVATE)

}
