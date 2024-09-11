package one.codium.wbpo.network.dto.movie_details

import one.codium.wbpo.network.entity.MovieDetails
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.Mappings
import org.mapstruct.factory.Mappers

@Mapper
interface MovieDetailsMapping {

    @Mappings(
        Mapping(target = "title", source = "originalTitle"),
        Mapping(target = "imagePath", source = "posterPath"),
    )
    fun getMovieDetails(dto: MovieDetailsDTO): MovieDetails

    companion object {
        val instance = Mappers.getMapper(MovieDetailsMapping::class.java)
    }

}
