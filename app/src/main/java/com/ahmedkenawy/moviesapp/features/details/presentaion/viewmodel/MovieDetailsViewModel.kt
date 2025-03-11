package com.ahmedkenawy.moviesapp.features.details.presentaion.viewmodel

import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableInt
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.ahmedkenawy.moviesapp.core.base.BaseCoroutineDispatchers
import com.ahmedkenawy.moviesapp.core.base.BaseViewModel
import com.ahmedkenawy.moviesapp.features.list.data.MoviesRepository
import com.ahmedkenawy.moviesapp.features.list.domain.Movie
import com.ahmedkenawy.moviesapp.utils.Constants.Intent.MOVIE_NAME
import com.ahmedkenawy.moviesapp.utils.databinding.ObservableString
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject



@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val moviesRepository: MoviesRepository,
    dispatchers: BaseCoroutineDispatchers
) : BaseViewModel<Any>(dispatchers) {

    /**
     * Movie details retrieved from the saved state.
     */
    val movieName: Movie? = savedStateHandle.get<Movie>(MOVIE_NAME)

    /**
     * Observable field for the movie poster path.
     */
    val poster = ObservableString()

    /**
     * Observable field for the movie title.
     */
    val title = ObservableString()

    /**
     * Observable field for the total votes of the movie.
     */
    val totalVotes = ObservableString()

    /**
     * Observable field for the average votes of the movie.
     */
    val totalAvgVotes = ObservableString()

    /**
     * Observable field for the overview of the movie.
     */
    val overView = ObservableString()

    /**
     * Observable field for the original Language of the movie.
     */
    val originalLanguage = ObservableString()

    /**
     * Observable field for the isFavorite of the movie.
     */
    val isFavorite = ObservableBoolean()

    /**
     * Observable field for id of the movie.
     */
    val id = ObservableInt()

    /**
     * Loads initial data when the ViewModel is created.
     */
    override fun loadInitialData() {
        loadMovieDetails()
    }

    fun updateMovie(movieId: Int, isFavorite: Boolean) {
        viewModelScope.launch {
            moviesRepository.updateMovie(movieId, isFavorite)
            this@MovieDetailsViewModel.isFavorite.set(isFavorite)
        }

    }

    /**
     * Loads movie details into observable fields.
     */
    private fun loadMovieDetails() {
        movieName?.let {
            title.set(it.title)
            totalVotes.set(it.vote_count.toString())
            totalAvgVotes.set(it.vote_average.toString())
            overView.set(it.overview)
            poster.set(it.poster_path)
            originalLanguage.set(it.originalLanguage)
            isFavorite.set(it.isFavorite)
            id.set(it.id)
        }
    }
}