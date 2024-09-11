package one.codium.wbpo.network.dto.movie

import com.google.gson.annotations.SerializedName

data class MovieListDTO(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val results: List<MovieDTO>
)
