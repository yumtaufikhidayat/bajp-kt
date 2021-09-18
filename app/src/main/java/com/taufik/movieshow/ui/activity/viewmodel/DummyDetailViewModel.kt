package com.taufik.movieshow.ui.activity.viewmodel

import androidx.lifecycle.ViewModel
import com.taufik.movieshow.data.MovieEntity
import com.taufik.movieshow.data.TvShowEntity
import com.taufik.movieshow.utils.DataDummy

class DummyDetailViewModel : ViewModel() {

    private var movieId: Int = 0
    private var tvShowsId: Int = 0

    // Showed on detail movie activity
    fun setSelectedMovie(movieId: Int) {
        this.movieId = movieId
    }

    fun getSelectedMovie(): MovieEntity {
        lateinit var movie: MovieEntity
        val movieEntities = DataDummy.generateMovieNowPlaying()
        for (movieEntity in movieEntities) {
            if (movieEntity.id == movieId) {
                movie = movieEntity
            }
        }
        return movie
    }

    // Showed on detail tv show activity
    fun setSelectedTvShows(tvShowsId: Int) {
        this.tvShowsId = tvShowsId
    }

    fun getSelectedTvShows(): TvShowEntity {
        lateinit var tvShows: TvShowEntity
        val tvShowsEntities = DataDummy.generateTvShowsAiringToday()
        for (tvShowsEntity in tvShowsEntities) {
            if (tvShowsEntity.id == tvShowsId) {
                tvShows = tvShowsEntity
            }
        }
        return tvShows
    }
}