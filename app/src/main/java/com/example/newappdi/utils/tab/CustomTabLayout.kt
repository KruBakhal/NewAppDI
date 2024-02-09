package com.writi.extension.custom.tab

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import com.example.newappdi.utils.setOnSingleClickListener

class CustomTabLayout @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private val tabList = mutableListOf<CustomTab>()

    fun addTab(tab: CustomTab) {
        super.addView(tab.rootView)
        tabList.add(tab)
        val params = tab.rootView.layoutParams as LayoutParams
        params.weight = 1F
        tab.rootView.layoutParams = params

        tab.rootView.setOnSingleClickListener {
            tab.viewModel?.tabId?.get()?.let { it1 ->
                setSelected(it1)
                tab.viewModel?.tabModel?.click?.invoke(it)
            }
        }
    }

    fun getTabAt(index: Int): CustomTab? {
        return if (index < tabList.size)
            tabList[index]
        else
            null
    }

    fun updateBadgeOnTab(index: Int, badge: String) {
        val tab = getTab(index)
        if (tab != null) {
//            val selectedTabs = tabList.filter { item -> item.viewModel?.selected?.get() == true }
//            selectedTabs.forEach { item ->
//                item.viewModel?.selected?.set(false)
//                tab.viewModel?.tabModel?.selected = false
//            }
            tabList
            tab.viewModel?.tabBadge?.set(badge)
        }
    }

    private fun getTab(id: Int): CustomTab? {
        return tabList.firstOrNull { item -> item.viewModel?.tabId?.get() == id }
    }

    fun setSelectedAt(index: Int) {
        val tab = getTabAt(index)
        if (tab != null) {
            val selectedTabs = tabList.filter { item -> item.viewModel?.selected?.get() == true }
            selectedTabs.forEach { item ->
                item.viewModel?.selected?.set(false)
                tab.viewModel?.tabModel?.selected = false
            }
            tab.viewModel?.selected?.set(true)
        }
    }

    fun setSelected(id: Int) {
        val tab = getTab(id)
        if (tab != null) {
            val selectedTabs = tabList.filter { item -> item.viewModel?.selected?.get() == true }
            selectedTabs.forEach { item ->
                item.viewModel?.selected?.set(false)
                tab.viewModel?.tabModel?.selected = false
            }
            tab.viewModel?.selected?.set(true)
        }
    }

    fun removeAllTabs() {
        tabList.clear()
        removeAllViews()
    }
}