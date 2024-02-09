package com.writi.extension.custom.tab

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import androidx.databinding.Observable
import com.example.newappdi.databinding.LayoutItemTabBinding


class CustomTab @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

/*
    private val semiBoldType = Typeface.createFromAsset(
        context.assets,
        "fonts/Gilroy-SemiBold.ttf"
    )
    private val normalType = Typeface.createFromAsset(
        context.assets,
        "fonts/Gilroy-Medium.ttf"
    )
*/

    private lateinit var binding: LayoutItemTabBinding

    var viewModel: TabViewModel? = null

    init {
        initControl(context)
    }

    private fun initControl(context: Context) {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        binding = LayoutItemTabBinding.inflate(inflater)
        viewModel = TabViewModel()
        binding.viewModel = viewModel

        viewModel?.selected?.addOnPropertyChangedCallback(object :
            Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                if (viewModel?.selected?.get() == true) {
//                    binding.tvName.typeface = semiBoldType
                } else {
//                    binding.tvName.typeface = normalType
                }
            }
        })
    }

    override fun getRootView(): View {
        return binding.root
    }

    override fun addView(child: View?) {
        super.addView(binding.root)
    }

    fun bindTab(tab: TabModel): CustomTab {
        viewModel?.tabModel = tab

        viewModel?.tabId?.set(tab.id)
        viewModel?.tabName?.set(tab.name)
        viewModel?.tabIcon?.set(tab.drawableRes)
        viewModel?.selected?.set(tab.selected)
        viewModel?.tabBadge?.set(tab.badge)

        invalidate()
        return this
    }
}