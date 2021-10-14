package com.taufik.movieshow.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.taufik.movieshow.data.source.local.LocalDataSource
import com.taufik.movieshow.data.source.local.entity.MovieEntity
import com.taufik.movieshow.data.source.local.entity.TvShowEntity
import com.taufik.movieshow.data.source.remote.ApiResponse
import com.taufik.movieshow.data.source.remote.RemoteDataSource
import com.taufik.movieshow.data.source.remote.response.MovieResponse
import com.taufik.movieshow.data.source.remote.response.TvShowResponse
import com.taufik.movieshow.utils.AppExecutors
import com.taufik.movieshow.vo.Resource

class MovieShowRepository private constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : MovieShowDataSource {

    companion object {
        @Volatile
        private var instance: MovieShowRepository? = null

        fun getInstance(
            remoteDataSource: RemoteDataSource,
            localData: LocalDataSource,
            appExecutors: AppExecutors
        ): MovieShowRepository =
            instance ?: synchronized(this) {
                instance ?: MovieShowRepository(remoteDataSource, localData, appExecutors).apply {
                    instance = this
                }
            }
    }

    override fun getAllMovies(): LiveData<Resource<PagedList<MovieEntity>>> {
        return object : NetworkBoundResource<PagedList<MovieEntity>, List<MovieResponse>>(appExecutors){
            override fun loadFromDB(): LiveData<PagedList<MovieEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()
                return LivePagedListBuilder(localDataSource.getMovies(), config).build()
            }

            override fun shouldFetch(data: PagedList<MovieEntity>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<MovieResponse>>> =
                remoteDataSource.getAllMovies()

            override fun saveCallResult(data: List<MovieResponse>) {
                val movieList = ArrayList<MovieEntity>()
                for (response in data) {
                    val movie = MovieEntity(
                        response.id,
                        response.imageBackdrop,
                        response.language,
                        response.overview,
                        response.imagePoster,
                        response.releaseDate,
                        response.title,
                        response.rating,
                        response.homePage,
                        false
                    )
                    movieList.add(movie)
                }

                localDataSource.insertMovies(movieList)
            }
        }.asLiveData()
    }

    override fun getFavoritedMovies(): LiveData<PagedList<MovieEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()
        return LivePagedListBuilder(localDataSource.getFavoriteMovies(), config).build()
    }

    override fun setMovieFavorite(movie: MovieEntity, state: Boolean) {
        appExecutors.diskIO().execute { localDataSource.setMovieFavorite(movie, state)}
    }


    override fun getAllTvShows(): LiveData<Resource<PagedList<TvShowEntity>>> {
        return object : NetworkBoundResource<PagedList<TvShowEntity>, List<TvShowResponse>>(appExecutors){
            override fun loadFromDB(): LiveData<PagedList<TvShowEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()
                return LivePagedListBuilder(localDataSource.getTvShows(), config).build()
            }

            override fun shouldFetch(data: PagedList<TvShowEntity>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<TvShowResponse>>> =
                remoteDataSource.getAllTvShows()

            override fun saveCallResult(data: List<TvShowResponse>) {
                val tvShowList = ArrayList<TvShowEntity>()
                for (response in data) {
                    val tvShow = TvShowEntity(
                        response.id,
                        response.imageBackdrop,
                        response.firstAirDate,
                        response.title,
                        response.language,
                        response.overview,
                        response.imagePoster,
                        response.rating,
                        response.homePage,
                        false
                    )
                    tvShowList.add(tvShow)
                }
                localDataSource.insertTvShows(tvShowList)
            }
        }.asLiveData()
    }

    override fun getFavoritedTvShows(): LiveData<PagedList<TvShowEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()
        return LivePagedListBuilder(localDataSource.getFavoriteTvShows(), config).build()
    }

    override fun setTvShowFavorite(tvShow: TvShowEntity, state: Boolean) {
        appExecutors.diskIO().execute { localDataSource.setTvShowFavorite(tvShow, state) }
    }

    override fun getDetailMovie(movieId: String): LiveData<MovieEntity> {
        val movieResult = MutableLiveData<MovieEntity>()
        remoteDataSource.getDetailMovies(movieId, object : RemoteDataSource.LoadDetailMoviesCallback{
            override fun onAllDetailMoviesReceived(
                movieId: String,
                movieResponse: List<MovieResponse>
            ) {
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
                            response.homePage,
                            false
                        )
                    }
                }

                movieResult.postValue(movie)
            }
        })

        return movieResult
    }

    override fun getDetailTvShow(tvShowId: String): LiveData<TvShowEntity> {
        val tvShowResult = MutableLiveData<TvShowEntity>()
        remoteDataSource.getDetailTvShows(tvShowId, object : RemoteDataSource.LoadDetailTvShowsCallback{
            override fun onAllDetailTvShowsReceived(
                tvShowId: String,
                tvShowResponse: List<TvShowResponse>
            ) {
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