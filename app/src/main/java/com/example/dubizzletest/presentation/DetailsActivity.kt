package com.example.dubizzletest.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.dubizzletest.R
import com.example.dubizzletest.databinding.ActivityDetailsBinding
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
        (getString(R.string.create_at) + parcelable?.createdAt).also { binding.createAt.text = it }
        (getString(R.string.price) + parcelable?.price).also { binding.price.text = it }
        (getString(R.string.name) + parcelable?.name).also { binding.name.text = it }
        (getString(R.string.uid) + parcelable?.uid).also { binding.uid.text = it }
        (getString(R.string.id) + parcelable?.imageIds?.get(0)).also { binding.id.text = it }
        Glide.with(binding.image).load(parcelable?.imageUrls?.get(0)).into(binding.image)
        Glide.with(binding.thumbnail).load(parcelable?.imageUrlsThumbnails?.get(0))
            .into(binding.thumbnail)
        binding.toolbar.setNavigationOnClickListener { v -> finish() }
    }
}