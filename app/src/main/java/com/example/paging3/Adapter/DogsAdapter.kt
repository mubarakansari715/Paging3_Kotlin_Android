package com.example.paging3.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.paging3.Data.Dogs
import com.example.paging3.databinding.EachRowBinding
import javax.inject.Inject

class DogsAdapter @Inject constructor() :
    PagingDataAdapter<Dogs, DogsAdapter.DogsViewHolder>(diff) {

    class DogsViewHolder(private val itemViewBinding: EachRowBinding) :
        RecyclerView.ViewHolder(itemViewBinding.root) {
        fun bind(dogs: Dogs) {
            itemViewBinding.apply {
                image.load(dogs.url)
            }
        }
    }

    override fun onBindViewHolder(holder: DogsViewHolder, position: Int) {
        val dogs = getItem(position)
        if (dogs != null) {
            holder.bind(dogs)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogsViewHolder {
        return (DogsViewHolder(EachRowBinding.inflate(LayoutInflater.from(parent.context),
            parent,
            false)))
    }

    object diff : DiffUtil.ItemCallback<Dogs>() {
        override fun areItemsTheSame(oldItem: Dogs, newItem: Dogs): Boolean =
            oldItem.url == newItem.url
        override fun areContentsTheSame(oldItem: Dogs, newItem: Dogs): Boolean = oldItem == newItem
    }
}


