package com.example.retrofitkotlincoroutines.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.retrofitkotlincoroutines.models.Post

class PostDiffUtil(val oldList: List<Post>, val newList: List<Post>) : DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldPost = oldList[oldItemPosition]
        val newPost = newList[newItemPosition]
        return oldPost.body == newPost.body &&
                oldPost.title == newPost.title &&
                oldPost.userId == newPost.userId &&
                oldPost.id == newPost.id
    }
}