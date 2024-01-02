package com.androiddevs.mvvmnewsapp.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.newappdi.NewsApp.Model.Article

@Dao
interface ArticleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(article: Article): Long

    @Query("SELECT * FROM articles")
    fun getAllArticles(): LiveData<List<Article>>

    @Query("SELECT * FROM articles where url=:url")
    fun geStatusArticle(url: String): Article

    @Query("DELETE FROM articles WHERE url = :article")
    suspend fun deleteArticle(article: String?)
}