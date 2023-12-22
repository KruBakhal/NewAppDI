package com.example.newappdi.NewsApp.UI.fragments

import android.adservices.topics.Topic
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.androiddevs.mvvmnewsapp.db.ArticleDatabase
import com.example.newappdi.NewsApp.Model.Article
import com.example.newappdi.R
import com.example.newappdi.NewsApp.Repository.NetworkRepo
import com.example.newappdi.NewsApp.Repository.NewsViewModelProviderFactory
import com.example.newappdi.NewsApp.ViewModel.NewViewModel
import com.example.newappdi.NewsApp.ViewModel.SampleViewModel
import com.example.newappdi.databinding.FragmentBreakingNewsBinding
import com.example.newappdi.databinding.FragmentDetailBinding
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [DetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class DetailFragment : Fragment() {
    private var artcile: Article?=null
    private lateinit var binding: FragmentDetailBinding
    private  val viewModel: NewViewModel by viewModels()

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
//    val bundle: DetailFragmentArgs by navArgs()

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

        binding= FragmentDetailBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        val networkRepo = NetworkRepo(ArticleDatabase(requireActivity()))
//        val viewModelProviderProvider = NewsViewModelProviderFactory(networkRepo)
//        viewModel = ViewModelProvider(requireActivity(), viewModelProviderProvider)
//            .get(SampleViewModel::class.java)

        viewModel.favStatus.value = arguments?.getBoolean("status") ?: false

        arguments?.getString("article")?.let {
            binding.webView.apply {
                webViewClient = WebViewClient()
                val json = it
                artcile = Gson().fromJson(json, Article::class.java)
                artcile?.url?.let { it1 -> loadUrl(it1) }
                viewModel.getStatusArticle(artcile!!)
            }

        }

        viewModel.favStatus.observe(viewLifecycleOwner, Observer { t ->
            if (t) {
                binding.btnFav.setColorFilter(android.R.color.holo_red_dark)
            } else {
                binding.btnFav.setColorFilter(R.color.white)
            }
        })

        binding.btnFav.setOnClickListener {
            if (viewModel.favStatus.value == true) {
                artcile?.let { it1 -> viewModel.deleteSaved(it1) }
            } else {
                artcile?.let { it1 -> viewModel.updateSaved(it1) }
            }
        }

    }


    /*companion object {
        */
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DetailFragment.
     *//*
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            DetailFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }*/
}