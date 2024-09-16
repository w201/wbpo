package one.codium.wbpo.core.entity.mapping

import one.codium.wbpo.core.db.entity.MovieDetailsEntity
import one.codium.wbpo.core.entity.MovieDetails
import one.codium.wbpo.network.dto.movie_details.MovieDetailsDTO
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.Mappings
import org.mapstruct.factory.Mappers

@Mapper
internal interface MovieDetailsMapping {

    @Mappings(
        Mapping(target = "title", source = "originalTitle"),
        Mapping(target = "imagePath", source = "posterPath"),
    )
    fun getMovieDetails(dto: MovieDetailsDTO): MovieDetails

    @Mappings(
        Mapping(target = "title", source = "originalTitle"),
        Mapping(target = "imagePath", source = "posterPath"),
    )
    fun getMovieDetailsEntity(dto: MovieDetailsDTO): MovieDetailsEntity

    fun getMovieDetailsEntity(entity: MovieDetailsEntity): MovieDetails


    companion object {
        val instance = Mappers.getMapper(MovieDetailsMapping::class.java)
    }

}
