package one.codium.wbpo.core.entity.mapping

import one.codium.wbpo.core.db.entity.MovieEntity
import one.codium.wbpo.core.entity.Movie
import one.codium.wbpo.network.dto.movie.MovieDTO
import org.mapstruct.*
import org.mapstruct.factory.Mappers

@Mapper
interface MovieMapping {

    fun getMovies(dtoList: List<MovieDTO>): List<Movie>

    @Mappings(
        Mapping(target = "favorite", ignore = true),
        Mapping(target = "isFavorite", ignore = true)
    )
    fun getMovies(dto: MovieDTO): Movie


    @Mapping(target = "page", expression = "java(page)")
    fun toMovieEntity(dto: MovieDTO, @Context page: Int): MovieEntity
    fun toMovieEntity(dtoList: List<MovieDTO>,@Context page: Int): List<MovieEntity>

    fun toMovie(entityList: List<MovieEntity>): List<Movie>
    fun toMovie(entity: MovieEntity): Movie

    companion object {
        val instance = Mappers.getMapper(MovieMapping::class.java)
    }

}
