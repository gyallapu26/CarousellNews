package com.example.carousellnews.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.carousellnews.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CarousellNewsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_carousell_news)
    }

}