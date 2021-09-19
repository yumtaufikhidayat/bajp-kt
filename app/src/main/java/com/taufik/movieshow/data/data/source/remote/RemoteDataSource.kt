package com.taufik.movieshow.data.data.source.remote

import android.os.Handler
import android.os.Looper
import com.taufik.movieshow.data.data.source.remote.response.MovieResponse
import com.taufik.movieshow.data.data.source.remote.response.TvShowResponse
import com.taufik.movieshow.utils.EspressoIdlingResource
import com.taufik.movieshow.utils.JsonHelper

class RemoteDataSource private constructor(private val jsonHelper: JsonHelper) {

    private val handler = Handler(Looper.getMainLooper())

    companion object {

        private const val SERVICE_LATENCY_IN_MILLIS: Long = 2_000

        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(helper: JsonHelper): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource(helper).apply {
                    instance = this
                }
            }
    }

    fun getAllMovies(callback: LoadMoviesCallback){
        EspressoIdlingResource.increment()
        handler.postDelayed({
            callback.onAllMoviesReceived(jsonHelper.loadMovies())
            EspressoIdlingResource.decrement()
        }, SERVICE_LATENCY_IN_MILLIS)
    }

    fun getAllTvShows(callback: LoadTvShowCallback) {
        EspressoIdlingResource.increment()
        handler.postDelayed({
            callback.onAllTvShowsReceived(jsonHelper.loadTvShow())
            EspressoIdlingResource.decrement()
        }, SERVICE_LATENCY_IN_MILLIS)
    }

    fun getDetailMovies(movieId: Int, callback: LoadDetailMoviesCallback){
        EspressoIdlingResource.increment()
        handler.postDelayed({
            callback.onAllDetailMoviesReceived(movieId, jsonHelper.loadMovies())
        }, SERVICE_LATENCY_IN_MILLIS)
    }

    fun getDetailTvShows(tvShowId: Int, callback: LoadDetailTvShowsCallback){
        EspressoIdlingResource.increment()
        handler.postDelayed({callback.onAllDetailTvShowsReceived(tvShowId, jsonHelper.loadTvShow())
        }, SERVICE_LATENCY_IN_MILLIS)
    }

    interface LoadMoviesCallback {
        fun onAllMoviesReceived(movieResponse: List<MovieResponse>)
    }

    interface LoadDetailMoviesCallback {
        fun onAllDetailMoviesReceived(movieId: Int, movieResponse: List<MovieResponse>)
    }

    interface LoadTvShowCallback {
        fun onAllTvShowsReceived(tvShowResponse: List<TvShowResponse>)
    }

    interface LoadDetailTvShowsCallback {
        fun onAllDetailTvShowsReceived(tvShowId: Int, tvShowResponse: List<TvShowResponse>)
    }
}