package com.example.dubizzletest.presentation;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.dubizzletest.R;
import com.example.dubizzletest.cacheFramework.BitmapUtils;
import com.example.dubizzletest.databinding.ActivityDetailsBinding;
import com.example.dubizzletest.model.ResultsItem;

import java.text.MessageFormat;

public class DetailsActivityJava extends AppCompatActivity {

    /**
     * VARIABLE DECLARATION
     */
    private ActivityDetailsBinding binding;

    /**
     * VIEW BINDING WITH ACTIVITY
     * INITIALISE VARIABLES
     */
    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailsBinding.inflate(LayoutInflater.from(this));
        setContentView(binding.getRoot());
        setData();
    }

    /**
     * INTENT DATA FROM MAIN CLASS
     * SETTING THE DATA TO THE DESIRED FIELDS
     */
    private void setData() {
        if (getIntent() == null) return;
        ResultsItem item = getIntent().getExtras().getParcelable("object");
        if (item != null) {
            if (!TextUtils.isEmpty(item.getCreatedAt()))
                binding.createAt.setText(MessageFormat.format("{0} {1}", getString(R.string.create_at), item.getCreatedAt()));
            if (!TextUtils.isEmpty(item.getPrice()))
                binding.price.setText(MessageFormat.format("{0} {1}", getString(R.string.price), item.getPrice()));
            if (!TextUtils.isEmpty(item.getName()))
                binding.name.setText(MessageFormat.format("{0} {1}", getString(R.string.name), item.getName()));
            if (!TextUtils.isEmpty(item.getUid()))
                binding.uid.setText(MessageFormat.format("{0} {1}", getString(R.string.uid), item.getUid()));
            if (item.getImageIds() != null && item.getImageIds().size() > 0 && !TextUtils.isEmpty(item.getImageIds().get(0)))
                binding.id.setText(MessageFormat.format("{0} {1}", getString(R.string.id), item.getImageIds().get(0)));
            if (item.getImageUrls() != null && item.getImageUrls().size() > 0) {
                //Cache Mechanism
                BitmapUtils.display(this, binding.image, item.getImageUrls().get(0));
                //Glide.with(binding.image).load(item.getImageUrls().get(0)).into(binding.image);
            }
            if (item.getImageUrlsThumbnails() != null && item.getImageUrlsThumbnails().size() > 0) {
                //Cache Mechanism
                BitmapUtils.display(this, binding.thumbnail, item.getImageUrlsThumbnails().get(0));
                //Glide.with(binding.thumbnail).load(item.getImageUrlsThumbnails().get(0)).into(binding.thumbnail);
            }
            binding.toolbar.setNavigationOnClickListener(v -> finish());
        }
    }
}
