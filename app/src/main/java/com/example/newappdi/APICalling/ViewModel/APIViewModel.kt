package com.example.newappdi.APICalling.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newappdi.APICalling.DI.APIRespository
import com.example.newappdi.APICalling.model.AppMainData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class APIViewModel @Inject constructor(val apiCallServices: APIRespository) : ViewModel() {

    val apiDataList = MutableLiveData<AppMainData>()
    fun getAppData() {
        viewModelScope.launch {
            val data = apiCallServices.getData()
            if (data.isSuccessful) {
                apiDataList.postValue(data.body())
            } else {

            }

        }
    }

}