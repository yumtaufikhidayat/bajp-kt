package com.taufik.movieshow.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.taufik.movieshow.R
import com.taufik.movieshow.data.model.DummyMovieMain
import com.taufik.movieshow.databinding.ItemsMovieShowBinding
import com.taufik.themovieshow.utils.Utils

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>(){

    private var listMovies = ArrayList<DummyMovieMain>()

    fun setMovies(movieResult: List<DummyMovieMain>) {
        this.listMovies.clear()
        this.listMovies.addAll(movieResult)
        notifyDataSetChanged()
    }

    inner class MovieViewHolder (private val binding: ItemsMovieShowBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movieResult: DummyMovieMain) {
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

//                itemView.setOnClickListener {
//                    val intent = Intent(itemView.context, DetailMovieActivity::class.java).apply {
//                        putExtra(DetailMovieActivity.EXTRA_DETAIL_ID, movieResult.id)
//                        putExtra(DetailMovieActivity.EXTRA_DETAIL_TITLE, movieResult.title)
//                    }
//                    it.context.startActivity(intent)
//                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val itemsMovieBinding = ItemsMovieShowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(itemsMovieBinding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val pos = listMovies[position]
        holder.bind(pos)
    }

    override fun getItemCount(): Int = listMovies.size
}