package com.ahmedkenawy.moviesapp.features.list.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ahmedkenawy.moviesapp.features.list.domain.Movie


@Database(entities = [Movie::class], version = 1)
abstract class MoviesDatabase : RoomDatabase() {

    /**
     * Retrieves the DAO interface for interacting with the movies table.
     *
     * @return The DAO interface for movies.
     */
    abstract fun moviesDao(): MoviesDao
}