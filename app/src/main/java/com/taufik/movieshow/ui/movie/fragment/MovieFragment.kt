package com.taufik.movieshow.ui.movie.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.taufik.movieshow.databinding.FragmentMovieBinding
import com.taufik.movieshow.ui.activity.ViewModelFactory
import com.taufik.movieshow.ui.movie.adapter.MovieAdapter
import com.taufik.movieshow.ui.movie.viewmodel.MovieViewModel

class MovieFragment : Fragment() {

    private var _binding: FragmentMovieBinding? = null
    private val binding get() = _binding

    private lateinit var viewModel: MovieViewModel
    private lateinit var movieAdapter: MovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMovieBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setData()
    }

    private fun setData() {

        showLoading(true)

        binding?.apply {

            if (activity != null) {

                val factory = ViewModelFactory.getInstance(requireActivity())
                viewModel = ViewModelProvider(
                    requireActivity(),
                    factory
                )[MovieViewModel::class.java]
                movieAdapter = MovieAdapter()
            }

            viewModel.getMovies().observe(viewLifecycleOwner, {
                Log.e(TAG, "setData: $it")
                showLoading(false)
                movieAdapter.setMovies(it)
            })
            
            with(rvMovie) {
                layoutManager = LinearLayoutManager(requireActivity())
                setHasFixedSize(true)
                adapter = movieAdapter
                Log.e(TAG, "setData: $movieAdapter")
            }
        }
    }

    private fun showLoading(state: Boolean) {

        binding?.apply {
            if (state) {
                progressBar.visibility = View.VISIBLE
            } else {
                progressBar.visibility = View.GONE
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val TAG = "MOVIE_FRAGMENT"
    }
}