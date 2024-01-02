package com.example.newappdi.api_calling

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.newappdi.api_calling.model.SubCategory
import com.example.newappdi.api_calling.Adapter.ImagesAdapter
import com.example.newappdi.api_calling.DI.Network.ErrorResponse
import com.example.newappdi.api_calling.DI.Network.SuccessResponse
import com.example.newappdi.api_calling.ViewModel.APIViewModel
import com.example.newappdi.NewsApp.Adapter.MoreAppAdapter
import com.example.newappdi.databinding.ActivityApicallBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pl.pzienowicz.autoscrollviewpager.AutoScrollViewPager

@AndroidEntryPoint
class APICallActivity : AppCompatActivity() {
    val viewModel: APIViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityApicallBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)


        CoroutineScope(Dispatchers.Main).launch {
            viewModel.getAppData()
        }

        viewModel.apiDataList.observe(this, Observer { data ->
            when (data) {
                is SuccessResponse -> {
                    data?.data?.let {
                        val sliderList = arrayListOf<SubCategory>()
                        val homeList = arrayListOf<SubCategory>()

                        it.appCenter?.let {
                            for (appCenter in it) {
                                if (!appCenter.subCategory.isNullOrEmpty()) {
                                    sliderList.addAll(appCenter.subCategory)
                                }
                            }
                        }
                        it.home?.let {
                            for (appCenter in it) {
                                if (!appCenter.subCategory.isNullOrEmpty()) {
                                    homeList.addAll(appCenter.subCategory)
                                }
                            }
                        }

                        binding.slider.adapter = ImagesAdapter(this, sliderList)
                        binding.slider.setInterval(2000)
                        binding.slider.setDirection(AutoScrollViewPager.Direction.RIGHT)
                        binding.slider.setCycle(true)
                        binding.slider.setBorderAnimation(true)
                        binding.slider.setSlideBorderMode(AutoScrollViewPager.SlideBorderMode.TO_PARENT)
                        binding.slider.startAutoScroll()
                        val homeAdapter = MoreAppAdapter()
                        binding.rvList.adapter = homeAdapter
                        homeAdapter.differ.submitList(homeList)
                    }
                }

                is ErrorResponse -> {
                    Log.d("TAG", "getAppData: onFailed Or Error")
                }

                else -> {

                }
            }


        })

    }
}