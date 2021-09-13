package com.example.carousellnews.di

import com.example.carousellnews.BuildConfig
import com.example.carousellnews.core.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class Module {

    @Provides
    @Singleton
    fun provideLoggingInterceptor(): Interceptor {
        return HttpLoggingInterceptor().also {
            it.level = HttpLoggingInterceptor.Level.BODY
        }
    }

    @Provides
    fun provideBaseUrl(): String = BuildConfig.BASE_URL


    @Provides
    @Singleton
    fun provideOkHttpClient(loggingInterceptor: Interceptor): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient, baseUrl: String): Retrofit =
        Retrofit
            .Builder()
            .client(okHttpClient)
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()


    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit) = retrofit.create(ApiService::class.java)
}