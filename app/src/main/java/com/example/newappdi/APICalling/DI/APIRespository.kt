package com.example.newappdi.APICalling.DI

import androidx.core.app.PendingIntentCompat.send
import com.example.newappdi.APICalling.DI.Network.APICallServices
import com.example.newappdi.APICalling.DI.Network.ErrorResponse
import com.example.newappdi.APICalling.DI.Network.Response
import com.example.newappdi.APICalling.DI.Network.SuccessResponse
import com.example.newappdi.APICalling.model.AppMainData
import java.util.concurrent.CancellationException
import javax.inject.Inject
import javax.inject.Named


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