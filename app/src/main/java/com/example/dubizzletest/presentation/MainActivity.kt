package com.example.dubizzletest.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dubizzletest.databinding.ActivityMainBinding
import com.example.dubizzletest.model.ResultData
import com.example.dubizzletest.presentation.adapter.ListAdapter
import com.example.dubizzletest.presentation.viewmodel.ListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: ListViewModel by viewModels()
    private lateinit var listAdapter: ListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
        callApi()
    }

    private fun callApi() {
        binding.progress.visibility = View.VISIBLE
        viewModel.listItems()
        viewModel.response.observe(this, {
            run {
                when (it) {
                    is ResultData.Success -> {
                        listAdapter.submitList(it.data?.results)
                        binding.progress.visibility = View.GONE
                    }
                    is ResultData.Loading -> {

                    }
                    else -> {
                    }
                }
            }
        })
    }

    private fun init() {
        listAdapter = ListAdapter(this)
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = listAdapter
        }
    }
}