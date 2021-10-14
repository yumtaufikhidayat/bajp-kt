package com.taufik.movieshow.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tvshowentities")
data class TvShowEntities(

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "tvShowId")
    val tvShowId: Int,

    @ColumnInfo(name = "imageBackdrop")
    val imageBackdrop: String,

    @ColumnInfo(name = "firstAirDate")
    val firstAirDate: String,

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "language")
    val language: String,

    @ColumnInfo(name = "overview")
    val overview: String,

    @ColumnInfo(name = "imagePoster")
    val imagePoster: String,

    @ColumnInfo(name = "rating")
    val rating: Double,

    @ColumnInfo(name = "homePage")
    val homePage: String,

    @ColumnInfo(name = "favorited")
    var favorited: Boolean = false
)