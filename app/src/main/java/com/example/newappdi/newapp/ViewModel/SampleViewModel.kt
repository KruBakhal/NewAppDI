package com.example.newappdi.NewsApp.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newappdi.NewsApp.Model.Article
import com.example.newappdi.NewsApp.Model.NewsResponse
import com.example.newappdi.NewsApp.Repository.Api_Client
import com.example.newappdi.NewsApp.Repository.NetworkRepo
import com.example.newappdi.NewsApp.Utils.Resource
import kotlinx.coroutines.launch
import retrofit2.Response


class SampleViewModel ( val newsRepository: NetworkRepo) : ViewModel() {


    private var breakingNewsResponse: NewsResponse? = null
    private var searchNewsResponse: NewsResponse? = null
    val breakingNew: MutableLiveData<Resource<NewsResponse>> = MutableLiveData()
    var breakingNewPage: Int = 1

    val searchNews: MutableLiveData<Resource<NewsResponse>> = MutableLiveData()
    var searchNewPage: Int = 1
    var favStatus = MutableLiveData<Boolean>()

    init {
        getBreakingNews("us")
    }

    fun getBreakingNews(countryCode: String) {
        viewModelScope.launch {
            breakingNew.postValue(Resource.Loading())
            val response =  Api_Client.api.getBreakingNews(countryCode, breakingNewPage)
            breakingNew.postValue(handleBreakingResponse(response))
        }

    }

    fun searchNews(searchQuery: String) = viewModelScope.launch {
        viewModelScope.launch {
            searchNews.postValue(Resource.Loading())
            val response = Api_Client.api.searchForNews(searchQuery, searchNewPage)
            searchNews.postValue(handleSearchNewsResponse(response))
        }
    }

    private fun handleBreakingResponse(response: Response<NewsResponse>): Resource<NewsResponse>? {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                breakingNewPage++
                if (breakingNewsResponse == null) {
                    breakingNewsResponse = resultResponse
                } else {
                    val oldArticles = breakingNewsResponse?.articles
                    val newArticles = resultResponse.articles
                    oldArticles?.addAll(newArticles)
                }
                return Resource.Success(breakingNewsResponse ?: resultResponse)
            }
        }
        breakingNewPage--
        if (breakingNewPage <= 0) breakingNewPage = 1
        return Resource.Error(response.message())
    }

    private fun handleSearchNewsResponse(response: Response<NewsResponse>): Resource<NewsResponse>? {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                searchNewPage++
                if (searchNewsResponse == null) {
                    searchNewsResponse = resultResponse
                } else {
                    val oldArticles = searchNewsResponse?.articles
                    val newArticles = resultResponse.articles
                    oldArticles?.addAll(newArticles)
                }
                return Resource.Success(searchNewsResponse ?: resultResponse)
            }
        }
        searchNewPage--
        if (searchNewPage <= 0) searchNewPage = 1
        return Resource.Error(response.message())
    }

    fun getSavedNews() = newsRepository.getSavedNews()

    fun deleteSaved(article: Article) =
        viewModelScope.launch {
            val status = newsRepository.deleteSavedNews(article.url)
            if (status != null) {
                favStatus.postValue(false)
            }
        }

    fun updateSaved(article: Article) = viewModelScope.launch {
        val status = newsRepository.insertNews(article)
        if (status != null) {
            favStatus.postValue(true)
        } else {
            favStatus.postValue(false)
        }
    }

    fun getStatusArticle(article: Article) = viewModelScope.launch {
        val status = newsRepository.getArticleStatus(article)
        if (status != null) {
            favStatus.postValue(true)
        } else {
            favStatus.postValue(false)
        }
    }

}