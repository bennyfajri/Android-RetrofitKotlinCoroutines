package com.example.retrofitkotlincoroutines.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitkotlincoroutines.databinding.ItemDataBinding
import com.example.retrofitkotlincoroutines.models.Post

class MyAdapter : RecyclerView.Adapter<MyAdapter.ViewHolder>() {

    private var myList = ArrayList<Post>()
    private lateinit var onItemClickCallback: OnItemClickCallback

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemDataBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(myList[position])
    }

    override fun getItemCount(): Int {
        return myList.size
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

    fun setData(newList: ArrayList<Post>) {
        myList = newList
        notifyDataSetChanged()
    }

    fun setOnITemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: Post)
    }
}