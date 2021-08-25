package com.taufik.movieshow.data.viewmodel

import androidx.lifecycle.ViewModel
import com.taufik.movieshow.data.model.DummyMovieMain
import com.taufik.movieshow.data.model.DummyTvShowMain
import com.taufik.movieshow.utils.DataDummy

class DummyViewModel : ViewModel() {

    // Showed on movie fragment
    fun getMovieNowPlaying(): List<DummyMovieMain> = DataDummy.generateMovieNowPlaying()

    // Showed on tv show fragment
    fun getTvShowsAiringToday(): List<DummyTvShowMain> = DataDummy.generateTvShowsAiringToday()
}