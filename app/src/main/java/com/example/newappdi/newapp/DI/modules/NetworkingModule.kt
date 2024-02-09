package com.example.newappdi.newapp.DI.modules

import android.content.Context
import androidx.room.Room
import com.androiddevs.mvvmnewsapp.db.ArticleDao
import com.androiddevs.mvvmnewsapp.db.ArticleDatabase
import com.example.newappdi.NewsApp.Repository.API_Interface
import com.example.newappdi.NewsApp.Utils.Constant
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkingModule {

    @Provides
    @Singleton
    fun getRetrofit(): Retrofit {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder().addInterceptor(logging).build()
        return Retrofit.Builder().baseUrl(Constant.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).client(client).build()
    }
    @Provides
    @Singleton
    fun getRetrofitService(clientr:Retrofit): API_Interface {

        return clientr.create(API_Interface::class.java)
    }

    @Provides
    @Singleton
    fun getRoomDatabase(@ApplicationContext context:Context): ArticleDatabase {

        return Room.databaseBuilder(
            context.applicationContext,
            ArticleDatabase::class.java,
            "article_db.db").allowMainThreadQueries().build()
    }


    @Provides
    @Singleton
    fun provideChannelDao( appDatabase: ArticleDatabase): ArticleDao {
        return appDatabase.getArticleDao()
    }
}