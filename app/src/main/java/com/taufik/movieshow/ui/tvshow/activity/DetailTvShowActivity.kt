package com.taufik.movieshow.ui.tvshow.activity

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
import com.taufik.movieshow.data.TvShowEntity
import com.taufik.movieshow.ui.activity.viewmodel.DummyDetailViewModel
import com.taufik.movieshow.databinding.ActivityDetailTvShowBinding
import com.taufik.movieshow.ui.movie.activity.DetailMovieActivity
import com.taufik.movieshow.utils.DataDummy
import com.taufik.movieshow.utils.Utils
import kotlin.properties.Delegates

class DetailTvShowActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_DETAIL_ID = "com.taufik.movieshow.ui.activity.EXTRA_DETAIL_ID"
        const val EXTRA_DETAIL_TITLE = "com.taufik.movieshow.ui.activity.EXTRA_DETAIL_TITLE"
    }

    private lateinit var binding: ActivityDetailTvShowBinding
    private lateinit var viewModel: DummyDetailViewModel
    private var id by Delegates.notNull<Int>()
    private lateinit var tvShowsTitle: String
    private lateinit var data: TvShowEntity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailTvShowBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setParcelableData()

        initActionBar()

        setData()

        setReadMore()
    }

    private fun setParcelableData() {
        id = intent.getIntExtra(DetailMovieActivity.EXTRA_DETAIL_ID, 0)
        tvShowsTitle = intent.getStringExtra(DetailMovieActivity.EXTRA_DETAIL_TITLE).toString()
    }

    private fun initActionBar() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setIcon(R.drawable.ic_arrow_back)
        supportActionBar?.title = tvShowsTitle
        supportActionBar?.elevation = 0F
    }

    private fun setData() {
        val extras = intent.extras
        if (extras != null) {
            val tvShowsId = extras.getInt(EXTRA_DETAIL_ID, 0)
            viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[DummyDetailViewModel::class.java]
            viewModel.setSelectedTvShows(tvShowsId)
            for (tvShows in DataDummy.generateTvShowsAiringToday()) {
                if (tvShows.id == tvShowsId) {
                    populateDetailTvShows(viewModel.getSelectedTvShows())
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

    private fun populateDetailTvShows(tvShowsEntity: TvShowEntity) {
        binding.apply {

            data = tvShowsEntity

            imgBackdrop.loadImage(tvShowsEntity.imageBackdrop)
            imgPoster.loadImage(tvShowsEntity.imagePoster)
            tvTitle.text = tvShowsEntity.title
            tvReleaseDate.text = tvShowsEntity.firstAirDate
            tvOverview.text = tvShowsEntity.overview
            tvRating.text = tvShowsEntity.rating.toString()
            tvLanguage.text = tvShowsEntity.language

            btnWebsite.text = getString(R.string.btnWebsites)
            btnWebsite.setOnClickListener {
                try {
                    val intentBrowser = Intent(Intent.ACTION_VIEW, Uri.parse(tvShowsEntity.homePage))
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
            .apply(
                RequestOptions.placeholderOf(R.drawable.ic_loading)
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

                    val body = "Visit this awesome shows \n${data.homePage}"

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