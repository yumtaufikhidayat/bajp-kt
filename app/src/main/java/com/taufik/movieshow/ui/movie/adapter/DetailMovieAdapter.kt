package com.taufik.movieshow.ui.movie.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.taufik.movieshow.data.source.local.entity.OtherMoviesEntity
import com.taufik.movieshow.databinding.ItemTopMovieShowBinding

class DetailMovieAdapter : RecyclerView.Adapter<DetailMovieAdapter.OtherMoviesViewHolder>() {

    private val listOtherMovies = ArrayList<OtherMoviesEntity>()

    fun setOtherMovies(otherMovies: List<OtherMoviesEntity>?) {
        if (otherMovies == null) return
        this.listOtherMovies.clear()
        this.listOtherMovies.addAll(otherMovies)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OtherMoviesViewHolder {
        val view = ItemTopMovieShowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return OtherMoviesViewHolder(view)
    }

    override fun onBindViewHolder(holder: OtherMoviesViewHolder, position: Int) {
        val otherMovies = listOtherMovies[position]
        holder.bind(otherMovies)
    }

    override fun getItemCount(): Int = listOtherMovies.size

    inner class OtherMoviesViewHolder(private val binding: ItemTopMovieShowBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(otherMovies: OtherMoviesEntity) {
            binding.apply {
                Glide.with(itemView.context)
                    .load(otherMovies.imagePoster)
                    .into(imgPoster)

                tvTitle.text = otherMovies.title
                tvYear.text = otherMovies.year
                tvRating.text = otherMovies.rating.toString()
            }
        }
    }
}