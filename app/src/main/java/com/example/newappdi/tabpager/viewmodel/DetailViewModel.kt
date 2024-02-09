package com.example.newappdi.tabpager.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.newappdi.tabpager.data.CategoryList
import com.example.newappdi.tabpager.data.ProductModel
import com.google.gson.Gson

class DetailViewModel(private val onSavedStateHandle: SavedStateHandle) : ViewModel() {

    val _productModel = mutableStateOf(ProductModel())
    val productModel get() = _productModel

    init {
        if (!onSavedStateHandle.get<String>("product").isNullOrEmpty()) {
            val json = onSavedStateHandle.get<String>("product")
            _productModel.value = if (!json.isNullOrEmpty()) {
                Gson().fromJson(json, ProductModel::class.java)
            } else {
                ProductModel()
            }
        } else {
            CategoryList()
        }
    }

}