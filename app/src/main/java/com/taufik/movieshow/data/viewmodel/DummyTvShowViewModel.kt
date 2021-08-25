package com.taufik.movieshow.data.viewmodel

import androidx.lifecycle.ViewModel
import com.taufik.movieshow.data.model.DummyTvShowMain
import com.taufik.themovieshow.utils.DataDummy

class DummyTvShowViewModel : ViewModel() {

    fun getTvShowsAiringToday(): List<DummyTvShowMain> = DataDummy.generateTvShowsAiringToday()
}