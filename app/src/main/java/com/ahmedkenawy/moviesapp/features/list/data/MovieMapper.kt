package com.ahmedkenawy.moviesapp.features.list.data

import com.ahmedkenawy.moviesapp.core.mapper.Mapper
import com.ahmedkenawy.moviesapp.features.list.data.remote.response.MoviesDto
import com.ahmedkenawy.moviesapp.features.list.domain.Movie
import javax.inject.Inject

/**
 * Mapper class responsible for mapping from [MoviesDto] to [Movie].
 */
class MovieMapper @Inject constructor() : Mapper<MoviesDto, Movie> {

    /**
     * Maps a [MoviesDto] object to a [Movie] object.
     * @param model The input [MoviesDto] object.
     * @return The mapped [Movie] object.
     */
    override fun map(model: MoviesDto): Movie {
        return Movie(
            id = model.id,
            overview = model.overview,
            popularity = model.popularity,
            poster_path = model.poster_path,
            title = model.title,
            vote_average = model.vote_average,
            vote_count = model.vote_count,
            releaseDate = model.release_date,
            originalLanguage = model.original_language
        )
    }
}
