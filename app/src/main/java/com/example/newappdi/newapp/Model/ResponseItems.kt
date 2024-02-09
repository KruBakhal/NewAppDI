package com.example.newappdi.newapp.Model

import com.squareup.moshi.Json

data class ResponseItems<T>(
    @field:Json(name = "articles")  val results: List<T>
)