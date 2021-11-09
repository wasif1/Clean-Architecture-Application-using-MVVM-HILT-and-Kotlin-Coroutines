package com.example.dubizzletest.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.dubizzletest.databinding.ActivityDetailsBinding
import com.example.dubizzletest.databinding.ActivityMainBinding
import com.example.dubizzletest.model.ResultsItem

class DetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setData()
    }

    private fun setData() {
        val parcelable = intent.extras?.getParcelable<ResultsItem>("object")
        binding.createAt.text = parcelable?.createdAt
        binding.price.text = parcelable?.price
        binding.name.text = parcelable?.name
        binding.uid.text = parcelable?.uid

        Glide.with(binding.image).load(parcelable?.imageUrls?.get(0)).into(binding.image)
        Glide.with(binding.thumbnail).load(parcelable?.imageUrlsThumbnails?.get(0))
            .into(binding.thumbnail)
    }
}