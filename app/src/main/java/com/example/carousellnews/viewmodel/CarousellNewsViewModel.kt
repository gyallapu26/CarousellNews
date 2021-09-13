package com.example.carousellnews.viewmodel

import androidx.lifecycle.viewModelScope
import com.example.carousellnews.base.BaseViewModel
import com.example.carousellnews.core.util.Result
import com.example.carousellnews.core.util.asFlow
import com.example.carousellnews.entities.News
import com.example.carousellnews.repository.CarousellNewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CarousellNewsViewModel @Inject constructor(private val repository: CarousellNewsRepository) :
    BaseViewModel() {


    /**
      Here the state flow used over shared flow because of the UI needs the current and
     subsequent states only and state flow ignores duplicate values which fits for our use case.
     */

    var resultMutableStateFlow: MutableStateFlow<Result<List<News>?>> =
        MutableStateFlow(Result.InProgress)

    init {
        fetchCarousellNews()
    }

    fun fetchCarousellNews(dispatcher: CoroutineDispatcher = this.dispatcher) {
        viewModelScope.launch {
            asFlow(dispatcher) {
                repository.fetchNews()
            }.collect{
                resultMutableStateFlow.value = it
            }
        }
    }

}