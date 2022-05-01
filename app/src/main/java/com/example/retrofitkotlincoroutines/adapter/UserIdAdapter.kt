package com.example.retrofitkotlincoroutines.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitkotlincoroutines.databinding.ItemUserIdBinding
import com.example.retrofitkotlincoroutines.models.UserId

class UserIdAdapter : RecyclerView.Adapter<UserIdAdapter.ViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback
    private var arrayList = arrayListOf<UserId>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemUserIdBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(arrayList[position])
    }

    inner class ViewHolder(private val binding: ItemUserIdBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(userId: UserId) {
            binding.chipIdUser.text = userId.userId
            binding.chipIdUser.setOnClickListener {
                onItemClickCallback.onItemClicked(userId)
            }
        }
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: UserId)
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    fun setData(newList: ArrayList<UserId>) {
        arrayList = newList
        notifyDataSetChanged()
    }
}