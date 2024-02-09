package com.example.newappdi.newapp.DI.network

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.newappdi.NewsApp.Model.Article
import com.example.newappdi.NewsApp.Model.NewsResponse
import com.example.newappdi.NewsApp.Repository.API_Interface
import com.example.newappdi.NewsApp.Utils.Constant
import com.example.newappdi.newapp.Model.MovieResponse
import com.example.newappdi.newapp.Repository.MoviesPagingSource
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject
const val NETWORK_PAGE_SIZE = 10

class Repository @Inject constructor(private val apiInterface: API_Interface) {


    suspend fun getBreakingNew(countryCode: String, pageNumber: Int): Response<NewsResponse> {
        return apiInterface.getBreakingNews(countryCode, pageNumber, Constant.API_KEY)
    }

    suspend fun getSearchNew(searchQuery: String, searchNewPage: Int): Response<NewsResponse> {
        return apiInterface.searchForNews(searchQuery, searchNewPage, Constant.API_KEY)
    }
     fun getMovies(): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                MoviesPagingSource(service = apiInterface)
            }
        ).flow
    }
}
