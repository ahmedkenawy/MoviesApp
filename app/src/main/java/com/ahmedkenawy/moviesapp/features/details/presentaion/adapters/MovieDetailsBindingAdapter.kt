package com.ahmedkenawy.moviesapp.features.details.presentaion.adapters

import android.widget.ImageView
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import coil.load
import coil.transform.RoundedCornersTransformation
import com.ahmedkenawy.moviesapp.R
import com.ahmedkenawy.moviesapp.features.details.presentaion.viewmodel.MovieDetailsViewModel

@BindingAdapter("setMovieImage")
fun setMovieImage(imageView: ImageView, poster: String?) {
    imageView.load("https://image.tmdb.org/t/p/original/$poster") {
        placeholder(R.drawable.ic_refresh)
        transformations(RoundedCornersTransformation())
        error(R.drawable.error_place_holder)
        crossfade(true)
    }
}

@BindingAdapter("movieId", "isFavorite", "viewModel")
fun updateMovieStatueFavorite(
    imageView: AppCompatImageView,
    id: Int,
    isFavorite: Boolean,
    viewModel: MovieDetailsViewModel
) {

    imageView.setOnClickListener {
        viewModel.updateMovie(id, !isFavorite)
        imageView.setImageResource(if (!isFavorite) R.drawable.ic_checked_favorite else R.drawable.ic_unchecked_favorite)
    }
}


