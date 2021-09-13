package com.example.carousellnews.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers


abstract class BaseViewModel : ViewModel() {
    internal val dispatcher: CoroutineDispatcher = Dispatchers.IO
}