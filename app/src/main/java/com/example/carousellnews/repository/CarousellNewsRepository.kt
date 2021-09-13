package com.example.carousellnews.repository


import com.example.carousellnews.core.ApiService
import javax.inject.Inject


class CarousellNewsRepository @Inject constructor(private val apiService: ApiService) {


    suspend fun fetchNews() = apiService.getCarousellNews()

}