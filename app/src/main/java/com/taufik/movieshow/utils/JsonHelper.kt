package com.taufik.movieshow.utils

import android.content.Context
import com.taufik.movieshow.data.source.remote.response.MovieResponse
import com.taufik.movieshow.data.source.remote.response.TvShowResponse
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException

class JsonHelper(private val context: Context) {

    private fun parsingFileToString(fileName: String): String? {
        return try {
            val `is` = context.assets.open(fileName)
            val buffer = ByteArray(`is`.available())
            `is`.read(buffer)
            `is`.close()
            String(buffer)
        } catch (e: IOException) {
            e.printStackTrace()
            null
        }
    }

    fun loadMovies(): List<MovieResponse> {
        val list = ArrayList<MovieResponse>()
        try {
            val responseObject = JSONObject(parsingFileToString("MovieResponse.json").toString())
            val listArray = responseObject.getJSONArray("results")
            for (i in 0 until listArray.length()) {
                val results = listArray.getJSONObject(i)

                val imageBackdrop = results.getString("imageBackdrop")
                val id = results.getInt("id")
                val language = results.getString("language")
                val overview = results.getString("overview")
                val imagePoster = results.getString("imagePoster")
                val releaseDate = results.getString("releaseDate")
                val title = results.getString("title")
                val rating = results.getDouble("rating")
                val homePage = results.getString("homePage")

                val movieResponse = MovieResponse(
                    imageBackdrop,
                    id,
                    language,
                    overview,
                    imagePoster,
                    releaseDate,
                    title,
                    rating,
                    homePage
                )
                list.add(movieResponse)
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }

        return list
    }

    fun loadTvShow(): List<TvShowResponse> {
        val list = ArrayList<TvShowResponse>()
        try {
            val responseObject = JSONObject(parsingFileToString("TvShowResponse.json").toString())
            val listArray = responseObject.getJSONArray("results")
            for (i in 0 until listArray.length()) {
                val results = listArray.getJSONObject(i)

                val imageBackdrop = results.getString("imageBackdrop")
                val firstAirDate = results.getString("firstAirDate")
                val id = results.getInt("id")
                val title = results.getString("title")
                val language = results.getString("language")
                val overview = results.getString("overview")
                val imagePoster = results.getString("imagePoster")
                val rating = results.getDouble("rating")
                val homePage = results.getString("homePage")

                val movieResponse = TvShowResponse(
                    imageBackdrop,
                    firstAirDate,
                    id,
                    title,
                    language,
                    overview,
                    imagePoster,
                    rating,
                    homePage
                )
                list.add(movieResponse)
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }

        return list
    }
}