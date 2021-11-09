package com.example.dubizzletest.presentation;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.dubizzletest.databinding.ActivityDetailsBinding;
import com.example.dubizzletest.model.ResultsItem;

public class DetailsActivityJava extends AppCompatActivity {

    private ActivityDetailsBinding binding;

    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailsBinding.inflate(LayoutInflater.from(this));
        setContentView(binding.getRoot());
        setData();
    }

    private void setData() {
        if(getIntent() == null) return;
        ResultsItem item = getIntent().getExtras().getParcelable("object");
        if (item != null) {
            if (!TextUtils.isEmpty(item.getCreatedAt()))
                binding.createAt.setText(item.getCreatedAt());
            if (!TextUtils.isEmpty(item.getPrice()))
                binding.price.setText(item.getPrice());
            if (!TextUtils.isEmpty(item.getName()))
                binding.name.setText(item.getName());
            if (!TextUtils.isEmpty(item.getUid()))
                binding.uid.setText(item.getUid());
            if (item.getImageUrls() != null && item.getImageUrls().size() > 0) {
                Glide.with(binding.image).load(item.getImageUrls().get(0)).into(binding.image);
            }
            if (item.getImageUrlsThumbnails() != null && item.getImageUrlsThumbnails().size() > 0) {
                Glide.with(binding.thumbnail).load(item.getImageUrlsThumbnails().get(0)).into(binding.thumbnail);
            }
        }
    }
}
