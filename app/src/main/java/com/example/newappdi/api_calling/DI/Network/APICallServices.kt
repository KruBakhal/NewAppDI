package com.example.newappdi.api_calling.DI.Network

import com.example.newappdi.api_calling.model.AppMainData
import retrofit2.http.GET

interface APICallServices {
    @GET("com.latest.status.message.text.jokes.funny")
    suspend fun getData(): AppMainData
}