package com.taufik.movieshow.ui.tvshow.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.taufik.movieshow.databinding.FragmentTvShowBinding
import com.taufik.movieshow.ui.activity.ViewModelFactory
import com.taufik.movieshow.ui.tvshow.adapter.TvShowAdapter
import com.taufik.movieshow.ui.tvshow.viewmodel.TvShowViewModel

class TvShowFragment : Fragment() {

    companion object {
        const val TAG = "TV_SHOW_FRAGMENT"
    }

    private var _binding: FragmentTvShowBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: TvShowViewModel
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

        setData()
    }

    private fun setData() {

        showLoading(true)

        binding.apply {

            if (activity != null) {

                val factory = ViewModelFactory.getInstance(requireActivity())
                viewModel = ViewModelProvider(requireActivity(),
                    factory
                )[TvShowViewModel::class.java]
                tvShowAdapter = TvShowAdapter()
            }

            viewModel.getTvShows().observe(viewLifecycleOwner, {
                Log.e(TAG, "setData: $it")
                showLoading(false)
                tvShowAdapter.setTvShows(it)
                tvShowAdapter.notifyDataSetChanged()
            })

            with(rvTvShow) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = tvShowAdapter
            }
        }
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