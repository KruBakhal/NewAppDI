package com.example.myappcompose.di.module

import com.example.newappdi.NewsApp.Utils.Constant
import com.example.newappdi.api_calling.DI.BaseUrl3ShopRetrofit
import com.example.newappdi.tabpager.network.APINetworkInterface
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    @BaseUrl3ShopRetrofit
    fun provideRetrofit(): Retrofit {
        val okHttpClientForMoreAppsView: OkHttpClient = OkHttpClient.Builder()
            .callTimeout(8, TimeUnit.SECONDS)
            .connectTimeout(8, TimeUnit.SECONDS)
            .readTimeout(50, TimeUnit.SECONDS)
            .writeTimeout(50, TimeUnit.SECONDS)
            .build()

        return Retrofit.Builder()
            .baseUrl(Constant.BASE_URL1)
            .client(provideOkHttpClient(provideLoggingInterceptor()))
            .client(okHttpClientForMoreAppsView)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()

    }

    @Provides
    @Singleton
    @BaseUrl3ShopRetrofit
    fun provideRetrofitService(@BaseUrl3ShopRetrofit retrofit: Retrofit): APINetworkInterface {
        return retrofit.create(APINetworkInterface::class.java)
    }


    fun provideOkHttpClient(interceptor: HttpLoggingInterceptor): OkHttpClient {
        val b = OkHttpClient.Builder()
        b.addInterceptor(interceptor)
        return b.build()
    }

    private fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }

}