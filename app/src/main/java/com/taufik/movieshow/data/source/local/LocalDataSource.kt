package com.taufik.movieshow.data.source.local

import androidx.paging.DataSource
import com.taufik.movieshow.data.source.local.room.MovieShowDao
import com.taufik.movieshow.data.source.local.entity.MovieEntity
import com.taufik.movieshow.data.source.local.entity.TvShowEntity

class LocalDataSource private constructor(private val movieShowDao: MovieShowDao){

    companion object {
        private var INSTANCE: LocalDataSource? = null

        fun getInstance(movieShowDao: MovieShowDao): LocalDataSource =
            INSTANCE ?: LocalDataSource(movieShowDao)
    }

    fun getMovies(): DataSource.Factory<Int, MovieEntity> = movieShowDao.getMovies()

    fun getFavoriteMovies(): DataSource.Factory<Int, MovieEntity> = movieShowDao.getFavoriteMovies()

    fun insertMovies(movies: List<MovieEntity>) = movieShowDao.insertMovies(movies)

    fun setMovieFavorite(movie: MovieEntity, newState: Boolean){
        movie.favorited = newState
        movieShowDao.updateMovies(movie)
    }

    fun getTvShows(): DataSource.Factory<Int, TvShowEntity> = movieShowDao.getTvShows()

    fun getFavoriteTvShows(): DataSource.Factory<Int, TvShowEntity> = movieShowDao.getFavoriteTvShows()

    fun insertTvShows(tvShows: List<TvShowEntity>) = movieShowDao.insertTvShows(tvShows)

    fun setTvShowFavorite(tvShow: TvShowEntity, newState: Boolean){
        tvShow.favorited = newState
        movieShowDao.updateTvShows(tvShow)
    }
}