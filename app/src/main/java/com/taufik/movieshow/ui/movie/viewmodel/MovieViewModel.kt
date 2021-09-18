package com.taufik.movieshow.ui.movie.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.taufik.movieshow.data.MovieEntity
import com.taufik.movieshow.data.source.MovieShowRepository

class MovieViewModel(private val movieShowRepository: MovieShowRepository) : ViewModel() {
    fun getMovies(): LiveData<List<MovieEntity>> = movieShowRepository.getAllMovies()
}