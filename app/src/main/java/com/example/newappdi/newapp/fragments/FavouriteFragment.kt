package com.example.newappdi.newapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newappdi.R
import com.example.newappdi.NewsApp.Adapter.NewAdapter
import com.example.newappdi.NewsApp.ViewModel.NewViewModel
import com.example.newappdi.databinding.FragmentFavouriteBinding
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


@AndroidEntryPoint
class FavouriteFragment : Fragment() {
    private lateinit var binding: FragmentFavouriteBinding
    private lateinit var newAdapter: NewAdapter
    private val viewModel: NewViewModel by viewModels()

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding= FragmentFavouriteBinding.inflate(inflater, container, false)

        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        val networkRepo = NetworkRepo(ArticleDatabase(requireActivity()))
//        val viewModelProviderProvider = NewsViewModelProviderFactory(networkRepo)
//        viewModel = ViewModelProvider(requireActivity(), viewModelProviderProvider)
//            .get(SampleViewModel::class.java)
        newAdapter = NewAdapter()
        newAdapter.setOnItemClickListner {
            val bundle = Bundle().apply {
                putBoolean("status", true)
//                putSerializable("article", it)
                putString("article", Gson().toJson(it))
            }
            findNavController().navigate(
                R.id.action_favouriteFragment_to_detailFragment,
                bundle
            )
        }
        binding.rvList.apply {
            adapter = newAdapter
            layoutManager = LinearLayoutManager(activity)
        }
        viewModel.getSavedNews().observe(viewLifecycleOwner, Observer { it ->
            binding.rvList.apply {
                newAdapter.differ.submitList(it)
            }
        })

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FavouriteFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FavouriteFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
