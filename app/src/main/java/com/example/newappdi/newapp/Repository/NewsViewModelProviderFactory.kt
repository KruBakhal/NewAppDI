package com.example.newappdi.NewsApp.Repository

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.newappdi.NewsApp.ViewModel.SampleViewModel


class NewsViewModelProviderFactory(
    val newsRepository: NetworkRepo
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SampleViewModel(newsRepository) as T
    }
}