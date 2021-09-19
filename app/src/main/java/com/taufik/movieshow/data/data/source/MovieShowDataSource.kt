package com.taufik.movieshow.data.data.source

import androidx.lifecycle.LiveData
import com.taufik.movieshow.data.MovieEntity
import com.taufik.movieshow.data.TvShowEntity

interface MovieShowDataSource {

    fun getAllMovies(): LiveData<List<MovieEntity>>

    fun getAllTvShows(): LiveData<List<TvShowEntity>>

    fun getDetailMovie(movieId: Int): LiveData<MovieEntity>

    fun getDetailTvShow(tvShowId: Int): LiveData<TvShowEntity>
}