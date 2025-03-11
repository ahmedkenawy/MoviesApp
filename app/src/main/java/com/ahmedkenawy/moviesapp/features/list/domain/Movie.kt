package com.ahmedkenawy.moviesapp.features.list.domain

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "movie")
@Parcelize
data class Movie(
    @PrimaryKey
    val id: Int,
    val overview: String?,
    val popularity: Double?,
    val poster_path: String?,
    val title: String?,
    val vote_average: Double?,
    val vote_count: Int?,
    val releaseDate: String?,
    val originalLanguage: String?,
    var isFavorite: Boolean = false
) : Parcelable