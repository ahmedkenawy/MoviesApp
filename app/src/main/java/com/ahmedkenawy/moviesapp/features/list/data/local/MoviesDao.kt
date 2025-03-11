package com.ahmedkenawy.moviesapp.features.list.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.ahmedkenawy.moviesapp.features.list.domain.Movie


@Dao
interface MoviesDao {

    /**
     * Retrieves all movies from the movies table.
     *
     * @return A list of all movies stored in the database.
     */
    @Query("SELECT * FROM movie")
    suspend fun getAllItems(): List<Movie>

    /**
     * Inserts movies into the movies table.
     *
     * @param movies The list of movies to be inserted.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(movies: List<Movie>)

    @Query("UPDATE movie SET isFavorite = :isFavorite WHERE id = :movieId")
    suspend fun updateFavoriteStatus(movieId: Int, isFavorite: Boolean)
}