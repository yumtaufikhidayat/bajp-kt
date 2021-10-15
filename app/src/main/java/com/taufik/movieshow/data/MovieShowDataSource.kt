package com.taufik.movieshow.data

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.taufik.movieshow.data.source.local.entity.*
import com.taufik.movieshow.vo.Resource

interface MovieShowDataSource {

    fun getAllMovies(): LiveData<Resource<PagedList<MovieEntity>>>

    fun getDetailMovie(movieId: String): LiveData<Resource<MovieWithOtherMovies>>

//    fun getDetailMovie(movieId: String): LiveData<MovieEntity>

    fun getFavoritedMovies(): LiveData<PagedList<MovieEntity>>

    fun setMovieFavorite(movie: MovieEntity, state: Boolean)

    fun setReadMovie(otherMovies: OtherMoviesEntity)

    fun getAllTvShows(): LiveData<Resource<PagedList<TvShowEntity>>>

    fun getDetailTvShow(tvShowId: String): LiveData<Resource<TvShowWithOtherTvShows>>

    fun getFavoritedTvShows(): LiveData<PagedList<TvShowEntity>>

    fun setTvShowFavorite(tvShow: TvShowEntity, state: Boolean)

    fun setReadTvShow(otherTvShow: OtherTvShowsEntity)

//    fun getDetailTvShow(tvShowId: String): LiveData<TvShowEntity>
}