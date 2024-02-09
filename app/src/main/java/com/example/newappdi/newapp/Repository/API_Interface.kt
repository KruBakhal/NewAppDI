package com.example.newappdi.NewsApp.Repository

import com.example.newappdi.NewsApp.Model.Article
import com.example.newappdi.NewsApp.Model.NewsResponse
import com.example.newappdi.NewsApp.Utils.Constant.Companion.API_KEY
import com.example.newappdi.newapp.Model.ResponseItems
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface API_Interface {
    @GET("v2/top-headlines")
    suspend fun getBreakingNews(
        @Query("country")
        countryCode: String = "us",
        @Query("page")
        pageNumber: Int = 1,
        @Query("apiKey")
        apiKey: String = API_KEY
    ): Response<NewsResponse>
    @GET("v2/top-headlines")
    suspend fun getBreakingNews1(
        @Query("country")
        countryCode: String = "us",
        @Query("page")
        pageNumber: Int = 1,
        @Query("apiKey")
        apiKey: String = API_KEY
    ): Response<NewsResponse>

    @GET("v2/everything")
    suspend fun searchForNews(
        @Query("q")
        searchQuery: String,
        @Query("page")
        pageNumber: Int = 1,
        @Query("apiKey")
        apiKey: String = API_KEY
    ): Response<NewsResponse>
}