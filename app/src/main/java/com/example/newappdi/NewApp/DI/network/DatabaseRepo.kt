package com.example.newappdi.NewsApp.Repository

import com.androiddevs.mvvmnewsapp.db.ArticleDao
import com.androiddevs.mvvmnewsapp.db.ArticleDatabase
import com.example.newappdi.NewsApp.Model.Article
import com.example.newappdi.NewsApp.Utils.Constant
import dagger.Module
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

class DatabaseRepo @Inject constructor(val db: ArticleDao) {


    fun getSavedNews() = db.getAllArticles()
    suspend fun deleteSavedNews(article: String?) = db.deleteArticle(article)
    suspend fun insertNews(article: Article) = db.upsert(article)
    suspend fun getArticleStatus(article: Article) = article.url?.let {
        db.geStatusArticle(
            it
        )
    }

}