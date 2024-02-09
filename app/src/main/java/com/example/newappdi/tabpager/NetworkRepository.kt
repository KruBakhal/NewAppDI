package com.example.newappdi.tabpager

import androidx.lifecycle.SavedStateHandle
import com.example.newappdi.api_calling.DI.BaseUrl3ShopRetrofit
import com.example.newappdi.tabpager.data.CategoryList
import com.example.newappdi.tabpager.data.ErrorResponse
import com.example.newappdi.tabpager.data.Response
import com.example.newappdi.tabpager.data.SuccessResponse
import com.example.newappdi.tabpager.network.APINetworkInterface
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject


class NetworkRepository @Inject constructor(
    @BaseUrl3ShopRetrofit val apiNetworkInterface: APINetworkInterface,
) {
    val _categoryList = MutableStateFlow<List<CategoryList>>(emptyList())
    val categoryList: StateFlow<List<CategoryList>> get() = _categoryList


    suspend fun getData(): Response<List<CategoryList>> {
        return try {
            val response = apiNetworkInterface.getData()
            _categoryList.value = response
          SuccessResponse(response)
        } catch (ee: Exception) {
            ErrorResponse("", ee)
        }
    }


}