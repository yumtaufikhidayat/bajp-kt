package com.taufik.movieshow.ui.activity

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import com.taufik.movieshow.R
import com.taufik.movieshow.utils.DataDummy
import com.taufik.movieshow.utils.EspressoIdlingResource
import org.junit.After
import org.junit.Before
import org.junit.Test

class MainActivityTest {

    private val dummyMovie = DataDummy.generateMovieNowPlaying()
    private val dummyTvShows = DataDummy.generateTvShowsAiringToday()

    @Before
    fun setUp() {
        ActivityScenario.launch(SplashscreenActivity::class.java)
        ActivityScenario.launch(MainActivity::class.java)
        IdlingRegistry.getInstance().register(EspressoIdlingResource.idlingResource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.idlingResource)
    }

    @Test
    fun loadMovies() {
        onView(withId(R.id.rvMovie)).check(matches(isDisplayed()))
        onView(withId(R.id.rvMovie)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyMovie.size))
    }

    @Test
    fun loadTvShows() {
        onView(withText("TV Shows")).perform(click())
        onView(withId(R.id.rvTvShow)).check(matches(isDisplayed()))
        onView(withId(R.id.rvTvShow)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyTvShows.size))
    }

    @Test
    fun loadDetailMovies() {
        onView(withId(R.id.rvMovie)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.imgBackdrop)).check(matches(isDisplayed()))
        onView(withId(R.id.imgPoster)).check(matches(isDisplayed()))
        onView(withId(R.id.tvTitle)).check(matches(isDisplayed()))
        onView(withId(R.id.tvTitle)).check(matches(withText(dummyMovie[0].title)))
        onView(withId(R.id.tvReleaseDate)).check(matches(isDisplayed()))
        onView(withId(R.id.tvReleaseDate)).check(matches(withText(dummyMovie[0].releaseDate)))
        onView(withId(R.id.tvOverview)).check(matches(isDisplayed()))
        onView(withId(R.id.tvOverview)).check(matches(withText(dummyMovie[0].overview)))
        onView(withId(R.id.tvRating)).check(matches(isDisplayed()))
        onView(withId(R.id.tvRating)).check(matches(withText(dummyMovie[0].rating.toString())))
        onView(withId(R.id.tvLanguage)).check(matches(isDisplayed()))
        onView(withId(R.id.tvLanguage)).check(matches(withText(dummyMovie[0].language)))
        onView(withId(R.id.tvReadMore)).perform(click())
        onView(withId(R.id.btnWebsite)).perform(click())
    }

    @Test
    fun loadDetailTvShows() {
        onView(withText("TV Shows")).perform(click())
        onView(withId(R.id.rvTvShow)).check(matches(isDisplayed()))
        onView(withId(R.id.rvTvShow)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.imgBackdrop)).check(matches(isDisplayed()))
        onView(withId(R.id.imgPoster)).check(matches(isDisplayed()))
        onView(withId(R.id.tvTitle)).check(matches(isDisplayed()))
        onView(withId(R.id.tvTitle)).check(matches(withText(dummyTvShows[0].title)))
        onView(withId(R.id.tvReleaseDate)).check(matches(isDisplayed()))
        onView(withId(R.id.tvReleaseDate)).check(matches(withText(dummyTvShows[0].firstAirDate)))
        onView(withId(R.id.tvOverview)).check(matches(isDisplayed()))
        onView(withId(R.id.tvOverview)).check(matches(withText(dummyTvShows[0].overview)))
        onView(withId(R.id.tvRating)).check(matches(isDisplayed()))
        onView(withId(R.id.tvRating)).check(matches(withText(dummyTvShows[0].rating.toString())))
        onView(withId(R.id.tvLanguage)).check(matches(isDisplayed()))
        onView(withId(R.id.tvLanguage)).check(matches(withText(dummyTvShows[0].language)))
        onView(withId(R.id.tvReadMore)).perform(click())
        onView(withId(R.id.btnWebsite)).perform(click())
    }
}