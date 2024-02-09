package com.example.newappdi.tabpager.data

import com.google.gson.annotations.SerializedName


data class CategoryList(

    @SerializedName("category_id") var categoryId: String? = null,
    @SerializedName("category_name") var categoryName: String? = null,
    @SerializedName("product_list") var productList: ArrayList<ProductModel> = arrayListOf(),
    @SerializedName("sub_category_name") var subCategoryName: String? = null

) {
    fun getImage(): String {
        if (!productList.isNullOrEmpty()) {
            return productList.get(0).cartImage.toString()
        }
        return ""
    }
}