package com.example.newappdi.api_calling.model

import com.google.gson.annotations.SerializedName

open class BaseApiResponse {

    @SerializedName("message")
    var message: String? = null

    @SerializedName("code")
    var code: String? = null

    @SerializedName("status")
    var status: String? = null
}