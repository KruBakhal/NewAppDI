package com.example.newappdi.APICalling

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.example.SubCategory
import com.example.newappdi.APICalling.Adapter.ImagesAdapter
import com.example.newappdi.APICalling.ViewModel.APIViewModel
import com.example.newappdi.NewsApp.Adapter.MoreAppAdapter
import com.example.newappdi.databinding.ActivityApicallBinding
import dagger.hilt.android.AndroidEntryPoint
import pl.pzienowicz.autoscrollviewpager.AutoScrollViewPager
@AndroidEntryPoint
class APICallActivity : AppCompatActivity() {
    val viewModel: APIViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityApicallBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        viewModel.getAppData()

        viewModel.apiDataList.observe(this, Observer {
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

        })
    }
}