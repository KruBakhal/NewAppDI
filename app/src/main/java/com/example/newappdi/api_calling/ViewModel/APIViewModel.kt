package com.example.newappdi.api_calling.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newappdi.api_calling.DI.APIRespository
import com.example.newappdi.api_calling.DI.Network.Response
import com.example.newappdi.api_calling.model.AppMainData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class APIViewModel @Inject constructor(
   val apiCallServices: APIRespository
) : ViewModel() {

    val apiDataList = MutableLiveData<Response<AppMainData>>()
    fun getAppData() {
        viewModelScope.launch {
            val data = apiCallServices.getData()
            apiDataList.postValue(data)
        }
    }

}