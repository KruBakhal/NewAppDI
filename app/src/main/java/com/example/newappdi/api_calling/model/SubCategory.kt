package com.example.newappdi.api_calling.model

import com.google.gson.annotations.SerializedName


data class SubCategory(

    @SerializedName("id") var id: Int? = null,
    @SerializedName("app_id") var appId: Int? = null,
    @SerializedName("position") var position: Int? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("icon") var icon: String? = null,
    @SerializedName("star") var star: String? = null,
    @SerializedName("installed_range") var installedRange: String? = null,
    @SerializedName("app_link") var appLink: String? = null,
    @SerializedName("banner") var banner: String? = null,
    @SerializedName("is_active") var isActive: Int? = null,
    @SerializedName("image_active") var imageActive: Int? = null,
    @SerializedName("banner_image") var bannerImage: String? = null

)