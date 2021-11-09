package com.example.dubizzletest.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dubizzletest.model.ListResponse
import com.example.dubizzletest.model.ResultData
import com.example.dubizzletest.usecase.ListUsecase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(private var usecase: ListUsecase) : ViewModel() {
    private val _response: MutableLiveData<ResultData<ListResponse>> = MutableLiveData()
    val response: LiveData<ResultData<ListResponse>> = _response

    fun listItems() = viewModelScope.launch {
        _response.value = usecase.getList()
    }
}