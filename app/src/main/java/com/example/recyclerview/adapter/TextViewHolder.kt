package com.example.recyclerview.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerview.databinding.ItemTextBinding
import com.example.recyclerview.model.TextItem

class TextViewHolder(private val binding: ItemTextBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: TextItem) {
        binding.textView.text = item.text
    }
}