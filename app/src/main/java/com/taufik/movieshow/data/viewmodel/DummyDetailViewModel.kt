package com.taufik.movieshow.data.viewmodel

import androidx.lifecycle.ViewModel
import com.taufik.movieshow.data.model.DummyMovieMain
import com.taufik.movieshow.data.model.DummyTvShowMain
import com.taufik.movieshow.utils.DataDummy

class DummyDetailViewModel : ViewModel() {

    private var movieId: Int = 0
    private var tvShowsId: Int = 0

    // Showed on detail movie activity
    fun setSelectedMovie(movieId: Int) {
        this.movieId = movieId
    }

    fun getSelectedMovie(): DummyMovieMain {
        lateinit var movie: DummyMovieMain
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

    fun getSelectedTvShows(): DummyTvShowMain {
        lateinit var tvShows: DummyTvShowMain
        val tvShowsEntities = DataDummy.generateTvShowsAiringToday()
        for (tvShowsEntity in tvShowsEntities) {
            if (tvShowsEntity.id == tvShowsId) {
                tvShows = tvShowsEntity
            }
        }
        return tvShows
    }
}