package com.example.retrofitkotlincoroutines.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitkotlincoroutines.databinding.ItemUserIdBinding
import com.example.retrofitkotlincoroutines.models.UserId

class UserIdAdapter : ListAdapter<UserId, UserIdAdapter.ViewHolder>(DIFF_CALLBACK) {

    private lateinit var onItemClickCallback: OnItemClickCallback

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemUserIdBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(private val binding: ItemUserIdBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(userId: UserId) {
            binding.chipIdUser.text = userId.userId
        }
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: UserId)
    }

    companion object {
        val DIFF_CALLBACK: DiffUtil.ItemCallback<UserId> =
            object : DiffUtil.ItemCallback<UserId>() {
                override fun areItemsTheSame(oldItem: UserId, newItem: UserId): Boolean {
                    return oldItem.userId == newItem.userId
                }

                override fun areContentsTheSame(oldItem: UserId, newItem: UserId): Boolean {
                    return oldItem == newItem
                }
            }
    }
}