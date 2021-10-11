package com.taufik.movieshow.data.source.local.room

import androidx.room.Dao

@Dao
interface MovieShowDao {

    fun getMovies()

    fun getFavoriteMovies()

    fun insertMovies()

    fun updateMovies()

    fun getTvShows()

    fun getFavoriteTvShows()

    fun insertTvShows()

    fun updateTvShows()
}