package com.example.newappdi.NewsApp.Repository

import com.androiddevs.mvvmnewsapp.db.ArticleDatabase
import com.example.newappdi.NewsApp.Model.Article
import com.example.newappdi.NewsApp.Utils.Constant

class NetworkRepo(val db: ArticleDatabase) {

    suspend fun getBreakingNew(countryCode: String, pageNumber: Int) =
        Api_Client.api.getBreakingNews(countryCode, pageNumber, Constant.API_KEY)

    suspend fun getSearchNew(searchQuery: String, pageNumber: Int) =
        Api_Client.api.searchForNews(searchQuery, pageNumber, Constant.API_KEY)

    fun getSavedNews() = db.getArticleDao().getAllArticles()
    suspend fun deleteSavedNews(article: String?) = db.getArticleDao().deleteArticle(article)
    suspend fun insertNews(article: Article) = db.getArticleDao().upsert(article)
    suspend fun getArticleStatus(article: Article) = article.url?.let {
        db.getArticleDao().geStatusArticle(
            it
        )
    }

}