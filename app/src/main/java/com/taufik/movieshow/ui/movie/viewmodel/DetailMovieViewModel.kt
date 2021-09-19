package com.taufik.movieshow.ui.movie.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.taufik.movieshow.data.MovieEntity
import com.taufik.movieshow.data.data.source.MovieShowRepository
import kotlin.properties.Delegates

class DetailMovieViewModel(private val movieShowRepository: MovieShowRepository) : ViewModel() {

    private var movieId by Delegates.notNull<Int>()

    fun setSelectedMovie(movieId: Int){
        this.movieId = movieId
    }

    fun getMovie(): LiveData<MovieEntity> = movieShowRepository.getDetailMovie(movieId)
}