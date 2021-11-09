package com.example.dubizzletest.model

sealed class ResultData<out T> {
    data class Success<out T>(val data: T? = null) : ResultData<T>()
    data class Loading(val nothing: Nothing? = null) : ResultData<Nothing>()
    data class Error<out T>(val message: String? = null) : ResultData<T>()
}