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
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.taufik.movieshow.R
import com.taufik.movieshow.data.source.local.entity.TvShowEntity
import com.taufik.movieshow.databinding.ActivityDetailTvShowBinding
import com.taufik.movieshow.ui.activity.ViewModelFactory
import com.taufik.movieshow.ui.tvshow.adapter.DetailTvShowAdapter
import com.taufik.movieshow.ui.tvshow.viewmodel.DetailTvShowViewModel
import com.taufik.movieshow.utils.Utils
import com.taufik.movieshow.vo.Status

class DetailTvShowActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailTvShowBinding
    private lateinit var viewModel: DetailTvShowViewModel
    private lateinit var parcelData: TvShowEntity
    private lateinit var data: TvShowEntity
    private var menu: Menu? = null

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
        parcelData = intent.getParcelableExtra(EXTRA_DETAIL)!!
    }

    private fun initActionBar() {
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setIcon(R.drawable.ic_arrow_back)
            elevation = 0F
            title = parcelData.title
        }
    }

    private fun setData() {
        val tvShowId = parcelData.tvShowId
        val factory = ViewModelFactory.getInstance(this)
        val detailTvShowAdapter = DetailTvShowAdapter()
        viewModel = ViewModelProvider(this, factory)[DetailTvShowViewModel::class.java]
        viewModel.setSelectedTvShow(tvShowId)

        binding.apply {
            progressBar.visibility = View.VISIBLE
            viewModel.otherTvShows.observe(this@DetailTvShowActivity, {
                if (it != null) {
                    when (it.status) {
                        Status.LOADING -> {
                            Log.e(TAG, "setData: $it")
                            progressBar.visibility = View.VISIBLE
                        }

                        Status.SUCCESS -> {
                            if (it.data != null) {
                                progressBar.visibility = View.GONE
                                Log.e(TAG, "setData: ${it.data.mOtherTvShows}")
                                detailTvShowAdapter.setOtherTvShows(it.data.mOtherTvShows)
                                populateDetailTvShows(it.data.mTvShows)
                            }
                        }

                        Status.ERROR -> {
                            progressBar.visibility = View.GONE
                            Log.e(TAG, "setData: $it")
                            Toast.makeText(
                                this@DetailTvShowActivity,
                                "Terjadi kesalahan",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
            })
        }

        with(binding.rvTopMovieShow) {
            isNestedScrollingEnabled = false
            layoutManager = LinearLayoutManager(this@DetailTvShowActivity, LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)
            adapter = detailTvShowAdapter
        }
//        viewModel.getTvShow().observe(this, {
//            if (it.tvShowId == tvShowId) {
//                Log.e(TAG, "setData: $it")
//                populateDetailTvShows(it)
//            }
//        })
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

            btnShare.text = getString(R.string.btnShare)
            btnShare.setOnClickListener {
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
        this.menu = menu
        binding.apply {
            viewModel.otherTvShows.observe(this@DetailTvShowActivity, {
                if (it != null) {
                    when (it.status) {
                        Status.LOADING -> progressBar.visibility = View.VISIBLE
                        Status.SUCCESS -> if (it.data != null) {
                            progressBar.visibility = View.GONE
                            val state = it.data.mTvShows.favorited
                            setFavorite(state)
                        }
                        Status.ERROR -> {
                            progressBar.visibility = View.GONE
                            Toast.makeText(
                                this@DetailTvShowActivity,
                                "Terjadi kesalahan",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
            })
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {

            android.R.id.home -> onBackPressed()

            R.id.nav_favorite -> {
                viewModel.setFavorite()
                return true
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

    private fun setFavorite(state: Boolean) {
        if (menu == null) return
        val menuItem = menu?.findItem(R.id.nav_favorite)
        if (state) {
            menuItem?.icon = ContextCompat.getDrawable(this, R.drawable.ic_favorite)
        } else {
            menuItem?.icon = ContextCompat.getDrawable(this, R.drawable.ic_favorite_detail)
        }
    }

    companion object {
        const val EXTRA_DETAIL = "com.taufik.movieshow.ui.tvshow.activity.EXTRA_DETAIL"
        const val TAG = "DETAIL_TV_SHOW_ACTIVITY"
    }
}