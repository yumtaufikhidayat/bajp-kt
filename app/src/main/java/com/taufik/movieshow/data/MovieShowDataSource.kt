package com.taufik.movieshow.data

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.taufik.movieshow.data.source.model.MovieEntity
import com.taufik.movieshow.data.source.model.TvShowEntity
import com.taufik.movieshow.vo.Resource

interface MovieShowDataSource {

    fun getAllMovies(): LiveData<Resource<PagedList<MovieEntity>>>

    fun getFavoritedMovies(): LiveData<PagedList<MovieEntity>>

    fun setMovieFavorite(movie: MovieEntity, state: Boolean)

    fun getAllTvShows(): LiveData<Resource<PagedList<TvShowEntity>>>

    fun getFavoritedTvShows(): LiveData<PagedList<TvShowEntity>>

    fun setTvShowFavorite(tvShow: TvShowEntity, state: Boolean)

    fun getDetailMovie(movieId: String): LiveData<MovieEntity>

    fun getDetailTvShow(tvShowId: String): LiveData<TvShowEntity>
}