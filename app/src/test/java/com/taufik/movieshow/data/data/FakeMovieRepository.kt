package com.taufik.movieshow.data.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.taufik.movieshow.data.MovieEntity
import com.taufik.movieshow.data.TvShowEntity
import com.taufik.movieshow.data.data.source.MovieShowDataSource
import com.taufik.movieshow.data.data.source.remote.RemoteDataSource
import com.taufik.movieshow.data.data.source.remote.response.MovieResponse
import com.taufik.movieshow.data.data.source.remote.response.TvShowResponse

class FakeMovieRepository (private val remoteDataSource: RemoteDataSource)
    : MovieShowDataSource {

    override fun getAllMovies(): LiveData<List<MovieEntity>> {
        val movieResults = MutableLiveData<List<MovieEntity>>()
        remoteDataSource.getAllMovies(object : RemoteDataSource.LoadMoviesCallback {
            override fun onAllMoviesReceived(movieResponse: List<MovieResponse>) {
                val movieList = ArrayList<MovieEntity>()
                for (response in movieResponse) {
                    val movie = MovieEntity(
                        response.imageBackdrop,
                        response.id,
                        response.language,
                        response.overview,
                        response.imagePoster,
                        response.releaseDate,
                        response.title,
                        response.rating,
                        response.homePage
                    )
                    movieList.add(movie)
                }
                movieResults.postValue(movieList)
            }
        })

        return movieResults
    }

    override fun getAllTvShows(): LiveData<List<TvShowEntity>> {
        val tvShowResults = MutableLiveData<List<TvShowEntity>>()
        remoteDataSource.getAllTvShows(object : RemoteDataSource.LoadTvShowCallback {
            override fun onAllTvShowsReceived(tvShowResponse: List<TvShowResponse>) {
                val tvShowList = ArrayList<TvShowEntity>()
                for (response in tvShowResponse) {
                    val tvShow = TvShowEntity(
                        response.imageBackdrop,
                        response.firstAirDate,
                        response.id,
                        response.title,
                        response.language,
                        response.overview,
                        response.imagePoster,
                        response.rating,
                        response.homePage
                    )
                    tvShowList.add(tvShow)
                }
                tvShowResults.postValue(tvShowList)
            }
        })

        return tvShowResults
    }

    override fun getDetailMovie(movieId: Int): LiveData<MovieEntity> {
        val movieResult = MutableLiveData<MovieEntity>()
        remoteDataSource.getAllMovies(object : RemoteDataSource.LoadMoviesCallback{
            override fun onAllMoviesReceived(movieResponse: List<MovieResponse>) {
                lateinit var movie: MovieEntity
                for (response in movieResponse) {
                    if (response.id == movieId) {
                        movie = MovieEntity(
                            response.imageBackdrop,
                            response.id,
                            response.language,
                            response.overview,
                            response.imagePoster,
                            response.releaseDate,
                            response.title,
                            response.rating,
                            response.homePage
                        )
                    }
                }

                movieResult.postValue(movie)
            }
        })

        return movieResult
    }

    override fun getDetailTvShow(tvShowId: Int): LiveData<TvShowEntity> {
        val tvShowResult = MutableLiveData<TvShowEntity>()
        remoteDataSource.getAllTvShows(object : RemoteDataSource.LoadTvShowCallback{
            override fun onAllTvShowsReceived(tvShowResponse: List<TvShowResponse>) {
                lateinit var tvShow: TvShowEntity
                for (response in tvShowResponse) {
                    if (response.id == tvShowId) {
                        tvShow = TvShowEntity(
                            response.imageBackdrop,
                            response.firstAirDate,
                            response.id,
                            response.title,
                            response.language,
                            response.overview,
                            response.imagePoster,
                            response.rating,
                            response.homePage
                        )
                    }
                }

                tvShowResult.postValue(tvShow)
            }
        })

        return tvShowResult
    }
}