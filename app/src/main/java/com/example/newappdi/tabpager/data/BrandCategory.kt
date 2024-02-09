package com.example.newappdi.tabpager.data

import com.google.gson.annotations.SerializedName


data class BrandCategory (

  @SerializedName("brandName"    ) var brandName    : String?                 = null,
  @SerializedName("categoryList" ) var categoryList : ArrayList<CategoryList> = arrayListOf()

)