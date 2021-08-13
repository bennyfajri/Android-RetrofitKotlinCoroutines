package com.example.retrofitkotlincoroutines

import android.icu.number.IntegerWidth
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.retrofitkotlincoroutines.Repository.Repository
import com.example.retrofitkotlincoroutines.adapter.MyAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private val myAdapter by lazy {
        MyAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupRecyclerView()

        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)

        viewModel.getCustomPosts(2, "id", "desc")
        viewModel.myCustomPost.observe(this, Observer { response ->
            if(response.isSuccessful){
                response.body()?.let { myAdapter.setData(it) }
                response.body()?.forEach{
                    Log.d("Response", it.userId.toString())
                    Log.d("Response", it.id.toString())
                    Log.d("Response", it.title)
                    Log.d("Response", it.body)
                    Log.d("Response", "================")
                }
            }else{
                Toast.makeText(applicationContext, response.code(), Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun setupRecyclerView() {
        rvData.layoutManager = LinearLayoutManager(applicationContext)
        rvData.adapter = myAdapter
    }
}