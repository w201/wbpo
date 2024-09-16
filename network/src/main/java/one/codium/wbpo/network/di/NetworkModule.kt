package one.codium.wbpo.network.di

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import one.codium.wbpo.network.BuildConfig
import one.codium.wbpo.network.MovieAPI
import one.codium.wbpo.network.repo.MovieNetworkRepo
import one.codium.wbpo.network.repo.MovieNetworkRepoImpl
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module(includes = [InternalNetworkModule::class])
@InstallIn(SingletonComponent::class)
interface NetworkModule

@Module
@InstallIn(SingletonComponent::class)
internal object InternalNetworkModule {

     @Provides
     @Singleton
     fun provideNetworkRepo(movieAPI: MovieAPI, @ApplicationContext context: Context): MovieNetworkRepo {
         return MovieNetworkRepoImpl(movieAPI, context)
     }

    @Provides
    @Singleton
    internal fun provideOkHttpClient(@ApplicationContext context: Context): OkHttpClient {
        val builder = OkHttpClient.Builder().apply {
            readTimeout(60, TimeUnit.SECONDS)
            writeTimeout(60, TimeUnit.SECONDS)
            addInterceptor { chain ->
                val original = chain.request()
                val request = original.newBuilder()
                    .addHeader("Authorization", "Bearer ${BuildConfig.MOVIE_TOKEN}")
                    .addHeader("Accept", "application/json")
                    .method(original.method, original.body)
                    .build()
                chain.proceed(request)
            }
            addInterceptor(ChuckerInterceptor.Builder(context).build())
        }
        return builder.build()
    }

    @Provides
    @Singleton
    internal fun provideRetrofit(
        okHttpClient: OkHttpClient
    ) = Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl(BuildConfig.MOVIE_URL)
        .addConverterFactory(GsonConverterFactory.create(Gson()))
        .build()

    @Provides
    @Singleton
    internal fun provideMovieAPI(retrofit: Retrofit): MovieAPI = retrofit.create(MovieAPI::class.java)
}
