package com.example.newappdi.APICalling.ViewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newappdi.APICalling.DI.APIRespository
import com.example.newappdi.APICalling.DI.Network.ErrorResponse
import com.example.newappdi.APICalling.DI.Network.SuccessResponse
import com.example.newappdi.APICalling.model.AppMainData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class APIViewModel @Inject constructor(
   val apiCallServices: APIRespository
) : ViewModel() {

    val apiDataList = MutableLiveData<AppMainData>()
    fun getAppData() {
        viewModelScope.launch {
            val data = apiCallServices.getData()
            when(data){
               is SuccessResponse ->{
                   if (data.data!=null) {
                       apiDataList.postValue(data.data)
                   } else {
                       Log.d("TAG", "getAppData: onFailed Or Error")
                   }
               }

                is ErrorResponse ->{

               }

                else -> {

                }
            }


        }
    }

}