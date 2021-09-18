package com.taufik.movieshow.ui.activity.viewmodel

import androidx.lifecycle.ViewModel
import com.taufik.movieshow.data.MovieEntity
import com.taufik.movieshow.data.TvShowEntity
import com.taufik.movieshow.utils.DataDummy

class DummyViewModel : ViewModel() {

    // Showed on movie fragment
    fun getMovieNowPlaying(): List<MovieEntity> = DataDummy.generateMovieNowPlaying()

    // Showed on tv show fragment
    fun getTvShowsAiringToday(): List<TvShowEntity> = DataDummy.generateTvShowsAiringToday()
}