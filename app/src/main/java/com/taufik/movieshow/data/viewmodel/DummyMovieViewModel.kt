package com.taufik.movieshow.data.viewmodel

import androidx.lifecycle.ViewModel
import com.taufik.movieshow.data.model.DummyMovieMain
import com.taufik.movieshow.utils.DataDummy

class DummyMovieViewModel : ViewModel() {

    fun getMovieNowPlaying(): List<DummyMovieMain> = DataDummy.generateMovieNowPlaying()
}