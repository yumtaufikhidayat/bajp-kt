package com.taufik.movieshow.data

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
)
