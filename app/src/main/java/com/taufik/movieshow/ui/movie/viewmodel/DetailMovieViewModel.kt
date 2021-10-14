package com.taufik.movieshow.ui.movie.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.taufik.movieshow.data.source.local.entity.MovieEntity
import com.taufik.movieshow.data.MovieShowRepository
import kotlin.properties.Delegates

class DetailMovieViewModel(private val movieShowRepository: MovieShowRepository) : ViewModel() {

    private var movieId by Delegates.notNull<String>()

    fun setSelectedMovie(movieId: String){
        this.movieId = movieId
    }

    fun getMovie(): LiveData<MovieEntity> = movieShowRepository.getDetailMovie(movieId)
}