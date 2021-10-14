package com.taufik.movieshow.ui.movie.adapter

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.taufik.movieshow.R
import com.taufik.movieshow.data.source.model.MovieEntity
import com.taufik.movieshow.databinding.ItemsMovieShowBinding
import com.taufik.movieshow.ui.movie.activity.DetailMovieActivity
import com.taufik.movieshow.utils.Utils

class MovieAdapter : PagedListAdapter<MovieEntity, MovieAdapter.MovieViewHolder>(DIFF_CALLBACK) {

    class MovieViewHolder(private val binding: ItemsMovieShowBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movieResult: MovieEntity) {
            with(binding) {
                Glide.with(itemView.context)
                    .load(Utils.IMAGE_URL + movieResult.imagePoster)
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loading)
                            .error(R.drawable.ic_error)
                    )
                    .into(imgPoster)

                tvTitle.text = movieResult.title
                tvReleaseDate.text = movieResult.releaseDate
                tvRating.text = movieResult.rating.toString()

                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailMovieActivity::class.java).apply {
                        putExtra(DetailMovieActivity.EXTRA_DETAIL, movieResult)
                    }
                    it.context.startActivity(intent)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val itemsMovieBinding =
            ItemsMovieShowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(itemsMovieBinding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val pos = getItem(position)
        if (pos != null) {
            Log.e(TAG, "onBindViewHolder: $pos")
            holder.bind(pos)
        }
    }

    companion object {
        private const val TAG = "MOVIE_ADAPTER"
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MovieEntity>(){
            override fun areItemsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean =
                oldItem.movieId == newItem.movieId

            override fun areContentsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean =
                oldItem == newItem
        }
    }
}