package com.example.retrofitkotlincoroutines.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitkotlincoroutines.R
import com.example.retrofitkotlincoroutines.models.Post
import kotlinx.android.synthetic.main.item_data.view.*

class MyAdapter: RecyclerView.Adapter<MyAdapter.ViewHolder>(){

    private var myList = emptyList<Post>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_data, parent, false)
        return  ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int){
        val data = myList[position]
        holder.itemView.tvUserId.text = data.userId.toString()
        holder.itemView.tvId.text = data.id.toString()
        holder.itemView.tvTitle.text = data.title
        holder.itemView.tvBody.text = data.body
    }

    override fun getItemCount(): Int {
        return myList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    fun setData(newList: List<Post>){
        myList = newList
        notifyDataSetChanged()
    }
}