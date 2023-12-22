package com.example.example

import android.R
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.google.gson.annotations.SerializedName


data class AppCenter(

    @SerializedName("id") var id: Int? = null,
    @SerializedName("position") var position: Int? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("is_active") var isActive: Int? = null,
    @SerializedName("catgeory") var catgeory: String? = null,
    @SerializedName("status") var status: Int? = null,
    @SerializedName("sub_category") var subCategory: ArrayList<SubCategory> = arrayListOf()

)