package com.taufik.movieshow.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.taufik.movieshow.data.viewmodel.DummyTvShowViewModel
import com.taufik.movieshow.databinding.FragmentTvShowBinding
import com.taufik.movieshow.ui.adapter.TvShowAdapter

class TvShowFragment : Fragment() {

    private var _binding: FragmentTvShowBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: DummyTvShowViewModel
    private lateinit var tvShowAdapter: TvShowAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTvShowBinding.inflate(inflater, container, false)
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
        tvShowAdapter = TvShowAdapter()
    }

    private fun setViewModel() {
        viewModel = ViewModelProvider(requireActivity(),
            ViewModelProvider.NewInstanceFactory()
        )[DummyTvShowViewModel::class.java]
    }

    private fun setRecyclerView() {
        with(binding.rvTvShow) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = tvShowAdapter
        }
    }

    private fun setData() {

        showLoading(true)
        val listPopular = viewModel.getTvShowsAiringToday()
        tvShowAdapter.setTvShows(listPopular)
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