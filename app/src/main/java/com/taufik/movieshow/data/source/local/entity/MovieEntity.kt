package com.taufik.movieshow.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movieentities")
data class MovieEntity(

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "movieId")
    var movieId: String,

    @ColumnInfo(name = "imageBackdrop")
    val imageBackdrop: String,

    @ColumnInfo(name = "language")
    val language: String,

    @ColumnInfo(name = "overview")
    val overview: String,

    @ColumnInfo(name = "imagePoster")
    val imagePoster: String,

    @ColumnInfo(name = "releaseDate")
    val releaseDate: String,

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "rating")
    val rating: Double,

    @ColumnInfo(name = "homePage")
    val homePage: String,

    @ColumnInfo(name = "favorite")
    var favorited: Boolean = false
)