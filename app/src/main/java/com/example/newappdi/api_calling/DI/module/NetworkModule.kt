package com.example.newappdi.api_calling.DI.module

import com.example.newappdi.api_calling.DI.BaseUrl2Retrofit
import com.example.newappdi.api_calling.DI.Network.APICallServices
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
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
@Named("api_call")
class NetworkModule {

    val BASEURL = "http://161.35.119.227/artwork_cache/api/AdvertiseNewApplications/17/"

    @Provides
    @Singleton
    @BaseUrl2Retrofit
    fun provideRetrofitClient(): Retrofit {
        var okHttpClient: OkHttpClient = OkHttpClient.Builder()
            .callTimeout(10, TimeUnit.SECONDS)
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(50, TimeUnit.SECONDS)
            .writeTimeout(50, TimeUnit.SECONDS)
            .build()

        var okHttpClientForMoreAppsView: OkHttpClient = OkHttpClient.Builder()
            .callTimeout(8, TimeUnit.SECONDS)
            .connectTimeout(8, TimeUnit.SECONDS)
            .readTimeout(50, TimeUnit.SECONDS)
            .writeTimeout(50, TimeUnit.SECONDS)
            .build()

        return Retrofit.Builder()
            .baseUrl(BASEURL)
            .client(provideOkHttpClient(provideLoggingInterceptor()))
            .client(okHttpClientForMoreAppsView)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()

    }

    @Provides
    @Singleton
    @BaseUrl2Retrofit
    fun provideRetrofitService(@BaseUrl2Retrofit clientr: Retrofit): APICallServices {
        return clientr.create(APICallServices::class.java)
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