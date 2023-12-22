package com.example.newappdi.APICalling.DI

import com.example.newappdi.APICalling.DI.Network.APICallServices
import com.example.newappdi.APICalling.model.AppMainData
import com.example.newappdi.NewsApp.Repository.Api_Client.Companion.api
import dagger.Binds
import dagger.BindsInstance
import retrofit2.Response
import retrofit2.http.GET
import javax.inject.Inject
import javax.inject.Named

@Named("api_call")

class APIRespository @Inject constructor(val apiCallServices: APICallServices) {
    @Binds
    suspend fun getData(): Response<AppMainData> {
        return apiCallServices.getData()
    }
}