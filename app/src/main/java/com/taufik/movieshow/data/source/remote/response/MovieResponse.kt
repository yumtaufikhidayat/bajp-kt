package com.taufik.movieshow.data.source.remote.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieResponse(
    val imageBackdrop: String,
    val id: Int,
    val language: String,
    val overview: String,
    val imagePoster: String,
    val releaseDate: String,
    val title: String,
    val rating: Double,
    val homePage: String
): Parcelable
