package com.taufik.movieshow.ui.tvshow.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.taufik.movieshow.data.source.model.TvShowEntity
import com.taufik.movieshow.data.MovieShowRepository
import kotlin.properties.Delegates

class DetailTvShowViewModel(private val movieShowRepository: MovieShowRepository) : ViewModel() {

    private var tvShowId by Delegates.notNull<String>()

    fun setSelectedTvShow(tvShowId: String) {
        this.tvShowId = tvShowId
    }

    fun getTvShow(): LiveData<TvShowEntity> = movieShowRepository.getDetailTvShow(tvShowId)
}