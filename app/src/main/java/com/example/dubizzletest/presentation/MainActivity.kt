package com.example.dubizzletest.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
    private lateinit var listAdapter : ListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        listAdapter = ListAdapter(this)
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = listAdapter
        }
        viewModel.listItems()
        viewModel.response.observe(this, {
            run {
                when (it) {
                    is ResultData.Success -> {
                        listAdapter.submitList(it.data?.results)
                    }
                    is ResultData.Loading -> {

                    }
                    else -> {
                    }
                }

            }
        })
    }
}