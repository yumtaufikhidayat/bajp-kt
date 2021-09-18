package com.taufik.movieshow.data.viewmodel

import com.taufik.movieshow.ui.activity.viewmodel.DummyViewModel
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class DummyViewModelTest {

    private lateinit var viewModel: DummyViewModel

    @Before
    fun setUp() {
        viewModel = DummyViewModel()
    }

    @Test
    fun getMovieNowPlaying() {
        val listNowPlaying = viewModel.getMovieNowPlaying()
        assertNotNull(listNowPlaying)
        assertEquals(20, listNowPlaying.size)
    }

    @Test
    fun getTvShowAiringToday() {
        val listPopular = viewModel.getTvShowsAiringToday()
        assertNotNull(listPopular)
        assertEquals(20, listPopular.size)
    }
}