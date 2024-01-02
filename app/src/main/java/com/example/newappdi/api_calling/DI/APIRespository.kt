package com.example.newappdi.api_calling.DI

import com.example.newappdi.api_calling.DI.Network.APICallServices
import com.example.newappdi.api_calling.DI.Network.ErrorResponse
import com.example.newappdi.api_calling.DI.Network.Response
import com.example.newappdi.api_calling.DI.Network.SuccessResponse
import com.example.newappdi.api_calling.model.AppMainData
import javax.inject.Inject


class APIRespository @Inject constructor(@BaseUrl2Retrofit val apiCallServices: APICallServices) {

    suspend fun getData(): Response<AppMainData> {
        return try {
            val response = apiCallServices.getData()
            SuccessResponse(response)
        } catch (ee: Exception) {
            ErrorResponse("", ee)
        }
    }


}