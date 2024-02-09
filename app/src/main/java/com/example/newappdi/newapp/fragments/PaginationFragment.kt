package com.example.newappdi.newapp.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newappdi.NewsApp.Adapter.NewAdapter
import com.example.newappdi.NewsApp.Utils.PaginationScroller
import com.example.newappdi.NewsApp.Utils.Resource
import com.example.newappdi.NewsApp.ViewModel.NewViewModel
import com.example.newappdi.R
import com.example.newappdi.databinding.FragmentBreakingNewsBinding
import com.example.newappdi.databinding.FragmentPaginationBinding
import com.example.newappdi.newapp.Adapter.MovieListAdapter
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PaginationFragment : Fragment() {
    companion object {
        const val QUERY_PAGE_SIZE = 20
    }

    private lateinit var binding: FragmentPaginationBinding
    lateinit var newAdapter: MovieListAdapter

    //    lateinit var viewModel: SampleViewModel
    val viewModel: NewViewModel by viewModels()
    var isLoading = false
    var isLastPage = false
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentPaginationBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("TAG", "onCreate: second called ")
        initView()
        collectUiState()
    }

    private fun initView() {
        newAdapter = MovieListAdapter()
        binding.rvList.adapter = newAdapter
    }

    private fun collectUiState() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.getMovies().collectLatest { movies ->
                hideProgressBar()
                newAdapter?.submitData(movies)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

    private fun hideProgressBar() {
        binding.paginationProgressBar.visibility = View.INVISIBLE
        isLoading = false
    }

    private fun showProgressBar() {
        binding.paginationProgressBar.visibility = View.VISIBLE
        isLoading = true
    }

}