package com.example.dubizzletest.domain

import com.example.dubizzletest.model.ListResponse
import com.example.dubizzletest.network.ApiService
import javax.inject.Inject

class ListRepository @Inject constructor(private val apiService: ApiService) {

    suspend fun getList(): ListResponse {
        return apiService.getList()
    }
}