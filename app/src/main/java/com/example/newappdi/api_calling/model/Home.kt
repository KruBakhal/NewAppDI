package com.example.example

import com.example.newappdi.api_calling.model.SubCategory
import com.google.gson.annotations.SerializedName


data class Home(

    @SerializedName("id") var id: Int? = null,
    @SerializedName("position") var position: Int? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("is_active") var isActive: Int? = null,
    @SerializedName("catgeory") var catgeory: String? = null,
    @SerializedName("status") var status: Int? = null,
    @SerializedName("sub_category") var subCategory: ArrayList<SubCategory> = arrayListOf()

)