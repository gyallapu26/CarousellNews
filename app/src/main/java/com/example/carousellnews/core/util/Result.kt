package com.example.carousellnews.core.util

import android.util.Log
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response


/**
 Since Sealed class provides the advantages of both enum and
 abstraction, here created three states for UI and wrapped the required
 response init.
 */
sealed class Result<out T> {

    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val errorMessage: String) : Result<Nothing>()
    object InProgress : Result<Nothing>()

}


/**
 * This is the function return flow of three states
 * one is [Result.InProgress] prior to an api call to show loder
 * secondly invoke the suspend function that is being passed and
 * Finally checks the api response and passes the [Result.Success] or
 * [Result.Error] in failure.
 * TODO -> Can customise the error response based on the bussiness use cases.
 */

fun <T> asFlow(dispatcher: CoroutineDispatcher, block: suspend () -> Response<T>) = flow {

    emit(Result.InProgress)
    val response = block.invoke()
    if (response.isSuccessful) emit(Result.Success(response.body()))
    else emit(Result.Error(response.errorBody().toString()))
}.flowOn(dispatcher)
