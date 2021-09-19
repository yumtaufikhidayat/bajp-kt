package com.taufik.movieshow.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieEntity(
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
