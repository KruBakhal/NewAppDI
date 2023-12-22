package com.example.newappdi.APICalling.model

import com.example.example.AppCenter
import com.example.example.Data
import com.example.example.Home
import com.example.example.NativeAdd
import com.example.example.TranslatorAdsId
import com.google.gson.annotations.SerializedName

data class AppMainData(
    @SerializedName("status") val status: Long,
    @SerializedName("message") val message: String,
    @SerializedName("data") val data: List<Data>,
    @SerializedName("is_home_enable") val isHomeEnable: Boolean,
    @SerializedName("app_center") val appCenter: List<AppCenter>,
    @SerializedName("home") val home: List<Home>,
    @SerializedName("more_apps") val moreApps: List<Any?>,
    @SerializedName("native_add") val nativeAdd: NativeAdd,
    @SerializedName("translator_ads_id") val translatorAdsId: TranslatorAdsId
)