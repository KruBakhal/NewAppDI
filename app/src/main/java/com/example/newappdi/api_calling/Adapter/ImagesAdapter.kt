package com.example.newappdi.api_calling.Adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import com.example.newappdi.api_calling.model.SubCategory

class ImagesAdapter(
    private val context: Context,
    private var images: List<SubCategory>
) : PagerAdapter() {

    override fun isViewFromObject(view: View, obj: Any): Boolean {
        return view == obj
    }

    override fun getCount() = images.size

    override fun instantiateItem(container: ViewGroup, position: Int): Any {

        val imageView = ImageView(context).apply {
            scaleType = ImageView.ScaleType.CENTER_CROP
        }

        Glide.with(context).load(images[position].icon).into(imageView)

        container.addView(imageView)

        return imageView
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View?)
    }
}