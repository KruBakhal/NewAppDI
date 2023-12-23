package com.example.newappdi.APICalling.DI.Network

import com.example.example.Data
import com.example.newappdi.APICalling.model.AppMainData
import com.google.gson.annotations.SerializedName
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface APICallServices {
    @GET("com.latest.status.message.text.jokes.funny")
    suspend fun getData(): AppMainData
}