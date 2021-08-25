package com.taufik.movieshow.ui.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.taufik.movieshow.R
import com.taufik.movieshow.data.model.DummyMovieMain
import com.taufik.movieshow.data.viewmodel.DummyViewModel
import com.taufik.movieshow.databinding.ActivityDetailMovieBinding
import com.taufik.movieshow.utils.DataDummy
import com.taufik.movieshow.utils.Utils
import kotlin.properties.Delegates

class DetailMovieActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_DETAIL_ID = "com.taufik.themovieshow.ui.main.movie.ui.activity.EXTRA_DETAIL_ID"
        const val EXTRA_DETAIL_TITLE = "com.taufik.themovieshow.ui.main.movie.ui.activity.EXTRA_DETAIL_TITLE"
    }

    private lateinit var binding: ActivityDetailMovieBinding
    private lateinit var viewModel: DummyViewModel
    private var id by Delegates.notNull<Int>()
    private lateinit var movieTitle: String
    private lateinit var data: DummyMovieMain

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setParcelableData()

        initActionBar()

        setData()

        setReadMore()
    }

    private fun setParcelableData() {
        id = intent.getIntExtra(EXTRA_DETAIL_ID, 0)
        movieTitle = intent.getStringExtra(EXTRA_DETAIL_TITLE).toString()
    }

    private fun initActionBar() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setIcon(R.drawable.ic_arrow_back)
        supportActionBar?.title = movieTitle
        supportActionBar?.elevation = 0F
    }

    private fun setData() {
        val extras = intent.extras
        if (extras != null) {
            val movieId = extras.getInt(EXTRA_DETAIL_ID, 0)
            viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[DummyViewModel::class.java]
            viewModel.setSelectedMovie(movieId)
            for (movie in DataDummy.generateMovieNowPlaying()) {
                if (movie.id == movieId) {
                    populateDetailMovie(viewModel.getSelectedMovie())
                }
            }
        }
    }

    private fun setReadMore() {
        binding.apply {
            tvReadMore.visibility = View.VISIBLE
            tvReadMore.setOnClickListener {
                if (tvReadMore.text.toString() == "Read More") {
                    tvOverview.maxLines = Integer.MAX_VALUE
                    tvOverview.ellipsize = null
                    tvReadMore.text = getString(R.string.tvReadLess)
                } else {
                    tvOverview.maxLines = 4
                    tvOverview.ellipsize = TextUtils.TruncateAt.END
                    tvReadMore.text = getString(R.string.tvReadMore)
                }
            }
        }
    }

    private fun populateDetailMovie(dummyMovieMain: DummyMovieMain) {
        binding.apply {

            data = dummyMovieMain

            imgBackdrop.loadImage(dummyMovieMain.imageBackdrop)
            imgPoster.loadImage(dummyMovieMain.imagePoster)
            tvTitle.text = dummyMovieMain.title
            tvReleasedOn.text = String.format("%s: %s", resources.getString(R.string.tvReleasedOn), dummyMovieMain.releaseDate)
            tvOverview.text = dummyMovieMain.overview
            tvRating.text = dummyMovieMain.rating.toString()
            tvLanguage.text = dummyMovieMain.language

            btnWebsite.text = getString(R.string.btnWebsites)
            btnWebsite.setOnClickListener {
                try {
                    val intentBrowser = Intent(Intent.ACTION_VIEW, Uri.parse(dummyMovieMain.homePage))
                    startActivity(Intent.createChooser(intentBrowser, "Open with:"))
                } catch (e: java.lang.Exception) {
                    Log.e("errorIntent", "onBindViewHolder: ${e.localizedMessage}")
                }
            }
        }
    }

    private fun ImageView.loadImage(url: String) {
        Glide.with(this.context)
            .load(Utils.IMAGE_URL + url)
            .apply(RequestOptions.placeholderOf(R.drawable.ic_loading)
                    .error(R.drawable.ic_error)
            )
            .into(this)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.detail_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {

            android.R.id.home -> onBackPressed()

            R.id.nav_share -> {
                try {

                    val body = "Visit this awesome movie \n${data.homePage}"

                    val shareIntent = Intent(Intent.ACTION_SEND)
                    shareIntent.type = "text/plain"
                    shareIntent.putExtra(Intent.EXTRA_TEXT, body)
                    startActivity(Intent.createChooser(shareIntent, "Share with:"))
                } catch (e: Exception) {
                    Log.e("shareFailed", "onOptionsItemSelected: ${e.localizedMessage}")
                }
            }

            R.id.nav_open_in_browser -> {
                try {
                    val intentBrowser = Intent(Intent.ACTION_VIEW, Uri.parse(data.homePage))
                    startActivity(Intent.createChooser(intentBrowser, "Open with:"))
                } catch (e: java.lang.Exception) {
                    Log.e("errorIntent", "onBindViewHolder: ${e.localizedMessage}")
                }
            }
        }

        return super.onOptionsItemSelected(item)
    }
}