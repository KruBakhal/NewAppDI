package com.example.newappdi.api_calling

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Observer
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import com.example.newappdi.api_calling.model.SubCategory
import com.example.newappdi.api_calling.Adapter.ImagesAdapter
import com.example.newappdi.api_calling.DI.Network.ErrorResponse
import com.example.newappdi.api_calling.DI.Network.SuccessResponse
import com.example.newappdi.api_calling.ViewModel.APIViewModel
import com.example.newappdi.R
import com.example.newappdi.databinding.ActivityApicallBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pl.pzienowicz.autoscrollviewpager.AutoScrollViewPager

@AndroidEntryPoint
class APICallActivity : ComponentActivity() {
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
                        binding.slider.startAutoScroll()/*  val homeAdapter = MoreAppAdapter()
                          binding.rvList.adapter = homeAdapter
                          homeAdapter.differ.submitList(homeList)*/
                        binding.composeView.setContent {

                            setupRV(homeList)
                        }
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

    @Composable
    private fun setupRV(homeList: ArrayList<SubCategory>) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            LazyColumn(
                Modifier
                    .fillMaxSize()
                    .background(
                        Color.White
                    )
            ) {
                items(homeList.toList()) {
                    MoreItemView(it)
                    Divider(
                        color = Color.White,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(0.dp, 10.dp)
                    )
                }
            }
        }
    }

    @OptIn(ExperimentalGlideComposeApi::class)
    @Composable
    fun MoreItemView(homeList: SubCategory) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(10.dp, 0.dp),
            elevation = 8.dp
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            ) {
                GlideImage(
                    modifier = Modifier
                        .width(90.dp)
                        .height(90.dp),
                    model = homeList.icon,
                    contentDescription = "",
                    loading = placeholder(R.drawable.wheel_arrow),
                    failure = placeholder(R.drawable.wheel_arrow)
                )
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                ) {
                    Text(text = homeList.name.toString())
                    Text(text = homeList.appLink.toString())
                    Text(text = homeList.appId.toString())
                }

            }


        }
    }


}

class NameProvider : PreviewParameterProvider<SubCategory> {
    override val values: Sequence<SubCategory>
        get() = sequenceOf(SubCategory(0, 0, 0, "Item 1"))
    override val count: Int
        get() = values.count()
}