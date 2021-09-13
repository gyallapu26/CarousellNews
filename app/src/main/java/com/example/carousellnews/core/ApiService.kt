package com.example.carousellnews.core

import com.example.carousellnews.entities.News
import retrofit2.Response
import retrofit2.http.GET


interface ApiService {

    @GET("android/carousell_news.json")
    suspend fun getCarousellNews(): Response<List<News>>

}