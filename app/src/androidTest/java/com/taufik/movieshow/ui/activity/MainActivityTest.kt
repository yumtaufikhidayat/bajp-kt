package com.taufik.movieshow.ui.activity

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.taufik.movieshow.R
import com.taufik.movieshow.utils.DataDummy
import org.junit.Rule
import org.junit.Test

class MainActivityTest {

    private val dummyMovie = DataDummy.generateMovieNowPlaying()
    private val dummyTvShows = DataDummy.generateTvShowsAiringToday()

    @get:Rule
    var activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun loadMovies() {
        onView(withId(R.id.rvMovie)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.rvMovie)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyMovie.size))
    }

    @Test
    fun loadTvShows() {
        onView(withText("TV Shows")).perform(ViewActions.click())
        onView(withId(R.id.rvTvShow)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.rvTvShow)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyTvShows.size))
    }

    @Test
    fun loadDetailMovies() {
        onView(withId(R.id.rvMovie)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, ViewActions.click()))
        onView(withId(R.id.imgBackdrop)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.imgPoster)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.tvTitle)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.tvTitle)).check(ViewAssertions.matches(withText(dummyMovie[0].title)))
        onView(withId(R.id.tvReleaseDate)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.tvReleaseDate)).check(ViewAssertions.matches(withText(dummyMovie[0].releaseDate)))
        onView(withId(R.id.tvOverview)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.tvOverview)).check(ViewAssertions.matches(withText(dummyMovie[0].overview)))
        onView(withId(R.id.tvRating)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.tvRating)).check(ViewAssertions.matches(withText(dummyMovie[0].rating.toString())))
        onView(withId(R.id.tvLanguage)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.tvLanguage)).check(ViewAssertions.matches(withText(dummyMovie[0].language)))
        onView(withId(R.id.tvReadMore)).perform(ViewActions.click())
        onView(withId(R.id.btnWebsite)).perform(ViewActions.click())
    }

    @Test
    fun loadDetailTvShows() {
        onView(withText("TV Shows")).perform(ViewActions.click())
        onView(withId(R.id.rvTvShow)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.rvTvShow)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, ViewActions.click()))
        onView(withId(R.id.imgBackdrop)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.imgPoster)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.tvTitle)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.tvTitle)).check(ViewAssertions.matches(withText(dummyTvShows[0].title)))
        onView(withId(R.id.tvReleaseDate)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.tvReleaseDate)).check(ViewAssertions.matches(withText(dummyTvShows[0].firstAirDate)))
        onView(withId(R.id.tvOverview)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.tvOverview)).check(ViewAssertions.matches(withText(dummyTvShows[0].overview)))
        onView(withId(R.id.tvRating)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.tvRating)).check(ViewAssertions.matches(withText(dummyTvShows[0].rating.toString())))
        onView(withId(R.id.tvLanguage)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(withId(R.id.tvLanguage)).check(ViewAssertions.matches(withText(dummyTvShows[0].language)))
        onView(withId(R.id.tvReadMore)).perform(ViewActions.click())
        onView(withId(R.id.btnWebsite)).perform(ViewActions.click())
    }
}