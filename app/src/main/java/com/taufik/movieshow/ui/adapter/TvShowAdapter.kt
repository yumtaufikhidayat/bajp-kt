package com.taufik.movieshow.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.taufik.movieshow.R
import com.taufik.movieshow.data.model.DummyTvShowMain
import com.taufik.movieshow.databinding.ItemsMovieShowBinding
import com.taufik.movieshow.utils.Utils

class TvShowAdapter : RecyclerView.Adapter<TvShowAdapter.TvShowsViewHolder>() {

    private var listTvShows = ArrayList<DummyTvShowMain>()

    fun setTvShows(tvShowPopularResult: List<DummyTvShowMain>) {
        this.listTvShows.clear()
        this.listTvShows.addAll(tvShowPopularResult)
        notifyDataSetChanged()
    }

    inner class TvShowsViewHolder(private val binding: ItemsMovieShowBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(tvShowPopularResult: DummyTvShowMain) {
            with(binding) {
                Glide.with(itemView.context)
                        .load(Utils.IMAGE_URL + tvShowPopularResult.imagePoster)
                        .apply(
                                RequestOptions.placeholderOf(R.drawable.ic_loading)
                                        .error(R.drawable.ic_error)
                        )
                        .into(imgPoster)

                tvTitle.text = tvShowPopularResult.title
                tvReleaseDate.text = tvShowPopularResult.firstAirDate
                tvRating.text = tvShowPopularResult.rating.toString()

//                itemView.setOnClickListener {
//                    val intent = Intent(itemView.context, DetailTvShowActivity::class.java).apply {
//                        putExtra(DetailTvShowActivity.EXTRA_DETAIL_ID, tvShowPopularResult.id)
//                        putExtra(DetailTvShowActivity.EXTRA_DETAIL_TITLE, tvShowPopularResult.title)
//                    }
//                    it.context.startActivity(intent)
//                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowsViewHolder {
        val itemsMovieShowBinding = ItemsMovieShowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TvShowsViewHolder(itemsMovieShowBinding)
    }

    override fun onBindViewHolder(holder: TvShowsViewHolder, position: Int) {
        val pos = listTvShows[position]
        holder.bind(pos)
    }

    override fun getItemCount(): Int = listTvShows.size
}