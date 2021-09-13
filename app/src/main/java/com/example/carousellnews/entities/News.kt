package com.example.carousellnews.entities


data class News(
    val title: String,
    val description: String,
    val banner_url: String,
    val time_created: Long,
    val rank: Int
)