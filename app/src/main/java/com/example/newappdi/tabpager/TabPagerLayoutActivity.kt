package com.example.newappdi.tabpager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.newappdi.NewsApp.Adapter.ShopAdapter
import com.example.newappdi.databinding.ActivityTabPagerLayoutBinding
import com.example.newappdi.databinding.FragmentCollectionObjectBinding
import com.example.newappdi.databinding.LayoutItemTab2Binding
import com.example.newappdi.databinding.LayoutItemTabBinding
import com.example.newappdi.tabpager.data.CategoryList
import com.example.newappdi.tabpager.data.ProductModel
import com.example.newappdi.tabpager.viewmodel.CategoryViewModel
import com.google.android.material.tabs.TabLayoutMediator
import com.google.gson.Gson
import com.writi.extension.custom.tab.CustomTab
import com.writi.extension.custom.tab.TabModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class TabPagerLayoutActivity : AppCompatActivity() {
    private lateinit var viewPager: ViewPager2
    private lateinit var demoCollectionAdapter: DemoCollectionAdapter
    private var tabOverview: Int = 0
    private lateinit var binding: ActivityTabPagerLayoutBinding
    val categoryViewModel: CategoryViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityTabPagerLayoutBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)


        lifecycleScope.launch {
            initViewModel()
        }

    }

    private suspend fun initViewModel() {
        categoryViewModel.categoryList.collectLatest { list ->
//            val tabs = mutableListOf<CustomTab>()

            /*list.forEachIndexed { index, categoryList ->
                val tabsTab = CustomTab(this).bindTab(
                    TabModel(
                        tabOverview,
                        categoryList.categoryName.toString(), ""
                    ) {

                        binding.viewPager.currentItem = index

                    })
                tabs.add(tabsTab)
                binding.tabLayout.addTab(tabsTab)
            }*/
            demoCollectionAdapter = DemoCollectionAdapter(this@TabPagerLayoutActivity, list)
            viewPager = binding.viewPager
            viewPager.adapter = demoCollectionAdapter

            viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageScrolled(
                    position: Int,
                    positionOffset: Float,
                    positionOffsetPixels: Int
                ) {
                    super.onPageScrolled(position, positionOffset, positionOffsetPixels)
                }

                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
//                    binding.tabLayout.po()
                }

                override fun onPageScrollStateChanged(state: Int) {
                    super.onPageScrollStateChanged(state)
                }
            })
            TabLayoutMediator(binding.tabLayout, viewPager) { tab, position ->
                val binding = LayoutItemTab2Binding.inflate(LayoutInflater.from(this))
                tab.customView = binding.root
                binding.viewModel = list.get(position)

            }.attach()
//            binding.tabLayout.setupWithViewPager(viewPager)

        }
    }

}

class DemoCollectionAdapter(fragment: AppCompatActivity, val list: List<CategoryList>) :
    FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = list.size

    override fun createFragment(position: Int): Fragment {
        // Return a NEW fragment instance in createFragment(int).
        val model = list.get(position)
        val fragment = DemoObjectFragment()
        fragment.arguments = Bundle().apply {
            // The object is just an integer.
            putString(ARG_OBJECT, Gson().toJson(model))
        }
        return fragment
    }
}

private const val ARG_OBJECT = "object"

// Instances of this class are fragments representing a single
// object in the collection.
class DemoObjectFragment : Fragment() {

    private lateinit var binding: FragmentCollectionObjectBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCollectionObjectBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        arguments?.takeIf { it.containsKey(ARG_OBJECT) }?.apply {

            val modelStr = getString(ARG_OBJECT).toString()
            val categoryList = if (!modelStr.isNullOrEmpty()) {
                Gson().fromJson(modelStr, CategoryList::class.java)
            } else {
                CategoryList()
            }
            val adapter = ShopAdapter()
            adapter.differ.submitList(categoryList.productList)
            binding.rvList.adapter = adapter
        }


    }
}