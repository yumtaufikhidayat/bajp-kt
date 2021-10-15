package com.taufik.movieshow.ui.tvshow.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.taufik.movieshow.data.source.local.entity.OtherTvShowsEntity
import com.taufik.movieshow.databinding.ItemTopMovieShowBinding

class DetailTvShowAdapter : RecyclerView.Adapter<DetailTvShowAdapter.OtherTvShowsViewHolder>() {

    private val listOtherTvShows = ArrayList<OtherTvShowsEntity>()

    fun setOtherTvShows(otherTvShows: List<OtherTvShowsEntity>?) {
        if (otherTvShows == null) return
        this.listOtherTvShows.clear()
        this.listOtherTvShows.addAll(otherTvShows)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OtherTvShowsViewHolder {
        val view = ItemTopMovieShowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return OtherTvShowsViewHolder(view)
    }

    override fun onBindViewHolder(holder: OtherTvShowsViewHolder, position: Int) {
        val otherTvShows = listOtherTvShows[position]
        holder.bind(otherTvShows)
    }

    override fun getItemCount(): Int = listOtherTvShows.size

    inner class OtherTvShowsViewHolder(private val binding: ItemTopMovieShowBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(otherTvShows: OtherTvShowsEntity) {
            binding.apply {
                Glide.with(itemView.context)
                    .load(otherTvShows.imagePoster)
                    .into(imgPoster)

                tvTitle.text = otherTvShows.title
                tvYear.text = otherTvShows.year
                tvRating.text = otherTvShows.rating.toString()
            }
        }
    }
}