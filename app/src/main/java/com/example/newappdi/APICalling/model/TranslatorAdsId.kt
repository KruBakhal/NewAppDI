package com.example.example

import com.google.gson.annotations.SerializedName


data class TranslatorAdsId(

    @SerializedName("banner") var banner: String? = null,
    @SerializedName("interstitial") var interstitial: String? = null

)