package com.taufik.movieshow.data.data.source

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doAnswer
import com.taufik.movieshow.data.data.FakeMovieRepository
import com.taufik.movieshow.data.data.source.remote.RemoteDataSource
import com.taufik.movieshow.utils.DataDummy
import com.taufik.movieshow.utils.LiveDataTestUtil
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

class MovieShowRepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = mock(RemoteDataSource::class.java)
    private val movieShowRepository = FakeMovieRepository(remote)

    private val movieResponses = DataDummy.generateMovieNowPlaying()
    private val movieId = movieResponses[0].id

    @Test
    fun getAllMovies() {
        doAnswer {
            (it.arguments[0] as RemoteDataSource.LoadMoviesCallback)
                .onAllMoviesReceived(movieResponses)
            null
        }.`when`(remote).getAllMovies(any())
        val movieEntities = LiveDataTestUtil.getValue(movieShowRepository.getAllMovies())
        verify(remote).getAllMovies(any())
        assertNotNull(movieEntities)
        assertEquals(movieResponses.size.toLong(), movieEntities.size.toLong())
    }

    @Test
    fun getDetailMovie() {
        doAnswer {
            (it.arguments[0] as RemoteDataSource.LoadMoviesCallback)
                .onAllMoviesReceived(movieResponses)
            null
        }.`when`(remote).getAllMovies(any())
        val movieEntities = LiveDataTestUtil.getValue(movieShowRepository.getDetailMovie(movieId))
        verify(remote).getAllMovies(any())
        assertNotNull(movieEntities)
        assertNotNull(movieEntities.id)
        assertEquals(movieResponses[0].id, movieEntities.id)
    }
}