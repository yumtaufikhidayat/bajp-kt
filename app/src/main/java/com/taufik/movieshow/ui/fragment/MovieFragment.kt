package com.taufik.movieshow.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.taufik.movieshow.data.viewmodel.DummyMovieViewModel
import com.taufik.movieshow.databinding.FragmentMovieBinding
import com.taufik.movieshow.ui.adapter.MovieAdapter

class MovieFragment : Fragment() {

    private var _binding: FragmentMovieBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: DummyMovieViewModel
    private lateinit var movieAdapter: MovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setAdapter()

        setViewModel()

        setRecyclerView()

        setData()
    }

    private fun setAdapter() {
        movieAdapter = MovieAdapter()
    }

    private fun setViewModel() {
        if (activity != null) {
            viewModel = ViewModelProvider(requireActivity(),
                ViewModelProvider.NewInstanceFactory()
            )[DummyMovieViewModel::class.java]
        }
    }

    private fun setRecyclerView() {
        with(binding.rvMovie) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = movieAdapter
        }
    }

    private fun setData() {

        showLoading(true)
        val listNowPlaying = viewModel.getMovieNowPlaying()
        movieAdapter.setMovies(listNowPlaying)
        showLoading(false)
    }

    private fun showLoading(state: Boolean) {

        binding.apply {
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
}