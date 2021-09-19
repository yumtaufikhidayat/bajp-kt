package com.taufik.movieshow.di

import android.content.Context
import com.taufik.movieshow.data.source.MovieShowRepository
import com.taufik.movieshow.data.source.remote.RemoteDataSource
import com.taufik.movieshow.utils.JsonHelper

object Injection {
    fun provideRepository(context: Context): MovieShowRepository {
        val remoteDataSource = RemoteDataSource.getInstance(JsonHelper(context))
        return MovieShowRepository.getInstance(remoteDataSource)
    }
}