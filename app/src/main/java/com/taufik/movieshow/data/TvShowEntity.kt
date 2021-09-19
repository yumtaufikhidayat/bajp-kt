package com.taufik.movieshow.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TvShowEntity(
    val imageBackdrop: String,
    val firstAirDate: String,
    val id: Int,
    val title: String,
    val language: String,
    val overview: String,
    val imagePoster: String,
    val rating: Double,
    val homePage: String
): Parcelable
