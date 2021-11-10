package com.example.dubizzletest.presentation.adapter

import android.content.Context
import android.content.Intent
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.dubizzletest.databinding.ItemMainBinding
import com.example.dubizzletest.model.ResultsItem
import com.example.dubizzletest.presentation.DetailsActivityJava

class ListAdapter(private val context : Context) : ListAdapter<ResultsItem, com.example.dubizzletest.presentation.adapter.ListAdapter.ViewHolder>(DataDiffCallBack()) {

    private class DataDiffCallBack : DiffUtil.ItemCallback<ResultsItem>() {
        override fun areItemsTheSame(oldItem: ResultsItem, newItem: ResultsItem): Boolean =
            oldItem.uid == newItem.uid

        override fun areContentsTheSame(oldItem: ResultsItem, newItem: ResultsItem): Boolean =
            oldItem == newItem
    }

    /**
     * CREATING VIEW
     * FOR EACH ITEM
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemMainBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    /**
     * VIEW BINDER
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(context, getItem(position))
    }

    /**
     * VIEW HOLDER
     * SET DATA TO VIEWS
     */
    class ViewHolder(private val binding: ItemMainBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(context: Context, item: ResultsItem) {
            binding.name.text = item.name
            if(item.imageUrlsThumbnails!!.isNotEmpty() && !TextUtils.isEmpty(item.imageUrlsThumbnails[0])) {
                Glide.with(binding.thumbnail).load(item.imageUrlsThumbnails[0])
                    .into(binding.thumbnail)
            }
            binding.root.setOnClickListener {
                //KOTLIN DETAILS ACTIVITY
                //context.startActivity(Intent(context, DetailsActivity::class.java).apply {
                //    putExtra("object", item)
                //})

                //JAVA DETAILS ACTIVITY - FOR BONUS PART 2
                context.startActivity(Intent(context, DetailsActivityJava::class.java).apply {
                    putExtra("object", item)
                })
            }
        }
    }
}