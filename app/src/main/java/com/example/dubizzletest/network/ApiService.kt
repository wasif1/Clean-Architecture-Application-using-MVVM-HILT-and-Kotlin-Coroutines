package com.example.dubizzletest.network

import com.example.dubizzletest.model.ListResponse
import retrofit2.http.GET

interface ApiService {

    @GET("/default/dynamodb-writer")
    suspend fun getList(): ListResponse

}