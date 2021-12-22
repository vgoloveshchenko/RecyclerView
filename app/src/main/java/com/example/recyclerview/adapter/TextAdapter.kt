package com.example.recyclerview.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerview.databinding.ItemTextBinding
import com.example.recyclerview.model.TextItem

// commented code when using AsyncListDiffer
class TextAdapter : RecyclerView.Adapter<TextViewHolder>() {

    private var currentItems: List<TextItem> = emptyList()

    private val differ: AsyncListDiffer<TextItem> = AsyncListDiffer(
        this,
        TextDiffItemCallback()
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextViewHolder {
        return TextViewHolder(
            binding = ItemTextBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: TextViewHolder, position: Int) {
//        holder.bind(differ.currentList[position])
        holder.bind(currentItems[position])
    }

    override fun getItemCount(): Int {
//        return differ.currentList.size
        return currentItems.size
    }

    fun setItems(items: List<TextItem>) {
//        differ.submitList(items)

        val callback = TextDiffCallback(currentItems, items)
        currentItems = items
        DiffUtil.calculateDiff(callback, false)
            .dispatchUpdatesTo(this)
    }

    private class TextDiffCallback(
        private val oldList: List<TextItem>,
        private val newList: List<TextItem>
    ) : DiffUtil.Callback() {

        override fun getOldListSize(): Int = oldList.size

        override fun getNewListSize(): Int = newList.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition] == newList[newItemPosition]
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition].text == newList[newItemPosition].text
        }
    }

    private class TextDiffItemCallback : DiffUtil.ItemCallback<TextItem>() {
        override fun areItemsTheSame(oldItem: TextItem, newItem: TextItem): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: TextItem, newItem: TextItem): Boolean {
            return oldItem.text == newItem.text
        }
    }
}