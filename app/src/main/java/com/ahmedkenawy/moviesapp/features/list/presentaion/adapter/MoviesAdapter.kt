package com.ahmedkenawy.moviesapp.features.list.presentaion.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import com.ahmedkenawy.moviesapp.R
import com.ahmedkenawy.moviesapp.databinding.ItemMoviesBinding
import com.ahmedkenawy.moviesapp.features.list.domain.Movie

/**
 * Adapter for displaying movies in a RecyclerView.
 *
 * @param onMovieClickListener Callback for handling movie item clicks.
 */
class MoviesAdapter(
    private val onMovieClickListener: (Int, Movie) -> Unit,
    private val addMovieToFavorite: (Int, Int, Boolean) -> Unit
) : ListAdapter<Movie, MoviesAdapter.MoviesViewHolder>(MyDiffUtil) {

    companion object MyDiffUtil : DiffUtil.ItemCallback<Movie>() {

        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.id == newItem.id
        }
    }

    /**
     * ViewHolder for movie items.
     *
     * @param binding View binding for the item layout.
     */
    inner class MoviesViewHolder(private val binding: ItemMoviesBinding) :
        RecyclerView.ViewHolder(binding.root) {

        /**
         * Binds movie data to the view.
         *
         * @param position The position of the item in the RecyclerView.
         * @param movie The movie data to bind.
         */
        fun bind(position: Int, movie: Movie) {
            with(binding) {
                setMovieData(movie)
                viewMovieDetails(position, movie)
                addToFavorite(this, movie, this@MoviesViewHolder)
            }
        }
    }

    private fun ItemMoviesBinding.setMovieData(movie: Movie) {
        movie.let {
            ivPoster.load("https://image.tmdb.org/t/p/original/${it.poster_path}") {
                placeholder(R.drawable.ic_refresh)
                transformations(RoundedCornersTransformation())
                error(R.drawable.error_place_holder)
                crossfade(true)
            }
            tvTitle.text = "${it.title}/${it.releaseDate}"
            tvTotalVotes.text = it.vote_count.toString()
            tvTotalVotesAverage.text = it.vote_average.toString()
            ivAddToFavorite.setImageResource(if (movie.isFavorite) R.drawable.ic_checked_favorite else R.drawable.ic_unchecked_favorite)
        }
    }

    private fun ItemMoviesBinding.viewMovieDetails(
        position: Int, movie: Movie
    ) {
        btnViewMore.setOnClickListener {
            onMovieClickListener(position, movie)
        }
    }

    private fun addToFavorite(
        itemMoviesBinding: ItemMoviesBinding, movie: Movie, moviesViewHolder: MoviesViewHolder
    ) {
        itemMoviesBinding.ivAddToFavorite.setOnClickListener {
            movie.isFavorite = !movie.isFavorite
            itemMoviesBinding.ivAddToFavorite.setImageResource(
                if (movie.isFavorite) R.drawable.ic_checked_favorite else R.drawable.ic_unchecked_favorite
            )
            notifyItemChanged(moviesViewHolder.adapterPosition, movie.isFavorite)
            addMovieToFavorite(moviesViewHolder.adapterPosition, movie.id, movie.isFavorite)
        }
    }

    /**
     * Creates a new ViewHolder for movie items.
     *
     * @param parent The parent ViewGroup.
     * @param viewType The type of the view.
     * @return A new instance of [MoviesViewHolder].
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val binding = ItemMoviesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MoviesViewHolder(binding)
    }

    /**
     * Binds movie data to the ViewHolder.
     *
     * @param holder The ViewHolder to bind.
     * @param position The position of the item in the RecyclerView.
     */
    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(position, it)
        }
    }
}