package com.ahmedkenawy.moviesapp.features.list.data

import com.ahmedkenawy.moviesapp.features.list.data.local.MoviesDatabase
import com.ahmedkenawy.moviesapp.features.list.data.remote.MoviesApi
import com.ahmedkenawy.moviesapp.features.list.domain.Movie
import com.ahmedkenawy.moviesapp.network.NetworkRouter
import com.ahmedkenawy.moviesapp.network.mapList
import javax.inject.Inject

class MoviesRepository @Inject constructor(
    private val api: MoviesApi,
    private val moviesDatabase: MoviesDatabase,
    private val movieMapper: MovieMapper
) {

    suspend fun fetchMovies() =
        NetworkRouter.invokeCall { api.fetchMovies() }
            .mapList(movieMapper)


    suspend fun fetchMoviesFromLocalDatabase(): List<Movie> =
        moviesDatabase.moviesDao().getAllItems()


    suspend fun insertMovies(movies: List<Movie>) =
        moviesDatabase.moviesDao().insertMovies(movies)

    suspend fun updateMovie(movieId: Int, isFavorite: Boolean) =
        moviesDatabase.moviesDao().updateFavoriteStatus(movieId, isFavorite)
}