package com.taufik.movieshow.ui.tvshow.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.taufik.movieshow.data.TvShowEntity
import com.taufik.movieshow.data.source.MovieShowRepository
import kotlin.properties.Delegates

class DetailTvShowViewModel(private val movieShowRepository: MovieShowRepository) : ViewModel() {

    private var tvShowId by Delegates.notNull<Int>()

    fun setSelectedTvShow(tvShowId: Int) {
        this.tvShowId = tvShowId
    }

    fun getTvShow(): LiveData<TvShowEntity> = movieShowRepository.getDetailTvShow(tvShowId)
}