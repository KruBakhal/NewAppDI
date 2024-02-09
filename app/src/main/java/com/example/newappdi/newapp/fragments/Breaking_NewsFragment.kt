package com.example.newappdi.newapp.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newappdi.R
import com.example.newappdi.NewsApp.Adapter.NewAdapter
import com.example.newappdi.NewsApp.Utils.PaginationScroller
import com.example.newappdi.NewsApp.Utils.Resource
import com.example.newappdi.NewsApp.ViewModel.NewViewModel
import com.example.newappdi.databinding.FragmentBreakingNewsBinding
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Breaking_NewsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class Breaking_NewsFragment : Fragment() {
    companion object {
        const val QUERY_PAGE_SIZE = 20
    }

    private lateinit var binding: FragmentBreakingNewsBinding
    lateinit var newAdapter: NewAdapter
//    lateinit var viewModel: SampleViewModel
    val viewModel: NewViewModel by viewModels()
    var isLoading = false
    var isLastPage = false
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding= FragmentBreakingNewsBinding.inflate(inflater, container, false)

        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("TAG", "onCreate: second called ")

//        viewModel = (activity as MainActivity).sampleViewModel
//        val networkRepo = NetworkRepo(ArticleDatabase(requireActivity()))
//        val viewModelProviderProvider = NewsViewModelProviderFactory(networkRepo)
//        viewModel = ViewModelProvider(requireActivity(), viewModelProviderProvider)
//            .get(SampleViewModel::class.java)

        newAdapter = NewAdapter()
        newAdapter.setOnItemClickListner {
            val bundle = Bundle().apply {
                putString("article", Gson().toJson(it))
            }
            findNavController().navigate(
                R.id.action_breaking_news_to_detailFragment,
                bundle
            )
        }
        binding.rvList.apply {
            adapter = newAdapter
            layoutManager = LinearLayoutManager(activity)
        }
        viewModel.breakingNew.observe(viewLifecycleOwner, Observer { response ->
            when (response) {
                is Resource.Success -> {
                    hideProgressBar()
                    response.data?.let { newsResponse ->
                        newAdapter.differ.submitList(newsResponse.articles.toList())
                        val totalPages = newsResponse.totalResults!! / QUERY_PAGE_SIZE + 2
                        isLastPage = viewModel.breakingNewPage == totalPages

                        if (isLastPage) {
                            binding.rvList.setPadding(0, 0, 0, 0)
                        }
                    }
                }
                is Resource.Error -> {
                    hideProgressBar()
                    response.message?.let { message ->
//                        Log.e(TAG, "An error occured: $message")
                    }
                }
                is Resource.Loading -> {
                    showProgressBar()
                }

                else -> {}
            }
        })

        binding.rvList.addOnScrollListener(object : PaginationScroller() {
            override fun loadMoreItems() {
                viewModel.getBreakingNews("us")
            }

            override fun isLastPage(): Boolean {
                return isLastPage
            }

            override fun isLoading(): Boolean {
                return isLoading
            }
        })
        viewModel.getBreakingNews("us")
    }

    private fun hideProgressBar() {
        binding.paginationProgressBar.visibility = View.INVISIBLE
        isLoading = false
    }

    private fun showProgressBar() {
        binding. paginationProgressBar.visibility = View.VISIBLE
        isLoading = true
    }


}