package com.example.example

import com.google.gson.annotations.SerializedName


data class Data(

    @SerializedName("id") var id: Int? = null,
    @SerializedName("app_id") var appId: Int? = null,
    @SerializedName("position") var position: Int? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("thumb_image") var thumbImage: String? = null,
    @SerializedName("app_link") var appLink: String? = null,
    @SerializedName("package_name") var packageName: String? = null,
    @SerializedName("full_thumb_image") var fullThumbImage: String? = null,
    @SerializedName("splash_active") var splashActive: Int? = null

)