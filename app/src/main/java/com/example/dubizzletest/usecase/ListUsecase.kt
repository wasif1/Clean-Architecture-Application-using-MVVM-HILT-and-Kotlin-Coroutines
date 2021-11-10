package com.example.dubizzletest.usecase

import com.example.dubizzletest.repository.ListRepository
import com.example.dubizzletest.model.ListResponse
import com.example.dubizzletest.model.ResultData
import javax.inject.Inject

class ListUsecase @Inject constructor(private val repository: ListRepository) {
    suspend fun getList(): ResultData<ListResponse> {
        val data = repository.getList()
        return ResultData.Success(data)
    }
}