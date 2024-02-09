package com.example.newappdi.tabpager.network

import com.example.newappdi.tabpager.data.CategoryList
import retrofit2.http.GET

interface APINetworkInterface {

    @GET("main/patanjali_products.json")
    suspend fun getData(): List<CategoryList>
}