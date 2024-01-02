package com.example.example

import com.google.gson.annotations.SerializedName


data class NativeAdd(

    @SerializedName("id") var id: Int? = null,
    @SerializedName("position") var position: Int? = null,
    @SerializedName("image") var image: String? = null,
    @SerializedName("playstore_link") var playstoreLink: String? = null,
    @SerializedName("package_name") var packageName: String? = null,
    @SerializedName("is_active") var isActive: Int? = null,
    @SerializedName("image_active") var imageActive: Int? = null

)