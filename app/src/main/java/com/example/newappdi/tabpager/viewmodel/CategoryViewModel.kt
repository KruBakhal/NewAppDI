package com.example.newappdi.tabpager.viewmodel

import androidx.core.app.NotificationCompat.getCategory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bumptech.glide.Glide.init
import com.example.newappdi.tabpager.NetworkRepository
import com.example.newappdi.tabpager.data.CategoryList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(
    val repository: NetworkRepository

) : ViewModel() {

    val categoryList: StateFlow<List<CategoryList>> get() = repository.categoryList

    init {
        getCategory()
    }

    fun getCategory() {
        viewModelScope.launch {
            repository.getData()
        }
    }


}