package one.codium.wbpo.network

import one.codium.wbpo.network.dto.movie.MovieListDTO
import one.codium.wbpo.network.dto.movie_details.MovieDetailsDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

internal interface MovieAPI {

    @GET("3/movie/popular?language=en-US&sort_by=popularity.desc")
    suspend fun getPopularMovies(@Query("page") page: Int): Response<MovieListDTO>

    @GET("3/movie/{movie_id}")
    suspend fun getMovie(@Path("movie_id") id: Int): Response<MovieDetailsDTO>

}
