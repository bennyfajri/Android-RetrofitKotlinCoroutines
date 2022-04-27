package com.example.retrofitkotlincoroutines.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitkotlincoroutines.databinding.ItemDataBinding
import com.example.retrofitkotlincoroutines.models.Post

class MyAdapter : ListAdapter<Post, MyAdapter.ViewHolder>(DIFF_CALLBACK) {

    private lateinit var onItemClickCallback: OnItemClickCallback

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemDataBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(private val binding: ItemDataBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(post: Post) {
            binding.tvUserId.text = post.userId.toString()
            binding.tvId.text = post.id.toString()
            binding.tvTitle.text = post.title
            binding.tvBody.text = post.body
            binding.itemData.setOnClickListener { onItemClickCallback.onItemClicked(post) }
        }
    }

    fun setOnITemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: Post)
    }

    companion object {
        val DIFF_CALLBACK: DiffUtil.ItemCallback<Post> =
            object : DiffUtil.ItemCallback<Post>() {
                override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean {
                    return oldItem.id == newItem.id
                }

                override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
                    return oldItem == newItem
                }
            }
    }
}