package com.taufik.movieshow.data.viewmodel

import com.taufik.movieshow.ui.activity.viewmodel.DummyDetailViewModel
import com.taufik.movieshow.utils.DataDummy
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class DummyDetailViewModelTest {
    
    private lateinit var viewModelMovie: DummyDetailViewModel
    private lateinit var viewModelTvShow: DummyDetailViewModel
    
    private val dummyDataMovie = DataDummy.generateMovieNowPlaying()[0]
    private val dummyDataTvShow = DataDummy.generateTvShowsAiringToday()[0]

    private val movieId = dummyDataMovie.id
    private val tvShowId = dummyDataTvShow.id

    @Before
    fun setUp() {
        viewModelMovie = DummyDetailViewModel()
        viewModelMovie.setSelectedMovie(movieId)

        viewModelTvShow = DummyDetailViewModel()
        viewModelTvShow.setSelectedTvShows(tvShowId)
    }

    @Test
    fun getSelectedMovie() {
        viewModelMovie.setSelectedMovie(dummyDataMovie.id)
        val tvShowsEntity = viewModelMovie.getSelectedMovie()
        assertNotNull(tvShowsEntity)
        assertEquals(dummyDataMovie.id, tvShowsEntity.id)
        assertEquals(dummyDataMovie.imageBackdrop, tvShowsEntity.imageBackdrop)
        assertEquals(dummyDataMovie.imagePoster, tvShowsEntity.imagePoster)
        assertEquals(dummyDataMovie.title, tvShowsEntity.title)
        assertEquals(dummyDataMovie.releaseDate, tvShowsEntity.releaseDate)
        assertEquals(dummyDataMovie.overview, tvShowsEntity.overview)
        assertEquals(dummyDataMovie.rating, tvShowsEntity.rating, 0.0)
        assertEquals(dummyDataMovie.language, tvShowsEntity.language)
    }

    @Test
    fun getSelectedTvShows() {
        viewModelTvShow.setSelectedTvShows(dummyDataTvShow.id)
        val tvShowsEntity = viewModelTvShow.getSelectedTvShows()
        assertNotNull(tvShowsEntity)
        assertEquals(dummyDataTvShow.id, tvShowsEntity.id)
        assertEquals(dummyDataTvShow.imageBackdrop, tvShowsEntity.imageBackdrop)
        assertEquals(dummyDataTvShow.imagePoster, tvShowsEntity.imagePoster)
        assertEquals(dummyDataTvShow.title, tvShowsEntity.title)
        assertEquals(dummyDataTvShow.firstAirDate, tvShowsEntity.firstAirDate)
        assertEquals(dummyDataTvShow.overview, tvShowsEntity.overview)
        assertEquals(dummyDataTvShow.rating, tvShowsEntity.rating, 0.0)
        assertEquals(dummyDataTvShow.language, tvShowsEntity.language)
    }
}