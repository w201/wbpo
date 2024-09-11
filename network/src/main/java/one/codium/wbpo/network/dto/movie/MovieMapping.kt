package one.codium.wbpo.network.dto.movie

import one.codium.wbpo.network.entity.Movie
import org.mapstruct.Mapper
import org.mapstruct.factory.Mappers

@Mapper
interface MovieMapping {

    fun getMovies(dtoList: List<MovieDTO>): List<Movie>
    fun getMovies(dto: MovieDTO): Movie

    companion object {
        val instance = Mappers.getMapper(MovieMapping::class.java)
    }

}
