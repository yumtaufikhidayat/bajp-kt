package com.taufik.movieshow.ui.tvshow.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.taufik.movieshow.data.TvShowEntity
import com.taufik.movieshow.data.source.MovieShowRepository

class TvShowViewModel(private val movieShowRepository: MovieShowRepository) : ViewModel() {

    fun getTvShows(): LiveData<List<TvShowEntity>> = movieShowRepository.getAllTvShows()
}