package com.example.retrofitkotlincoroutines

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.retrofitkotlincoroutines.adapter.MyAdapter
import com.example.retrofitkotlincoroutines.databinding.ActivityMainBinding
import com.example.retrofitkotlincoroutines.models.Post


class  MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    private val myAdapter by lazy {
        MyAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        val factory: MainViewModelFactory = MainViewModelFactory.getInstance()
        val viewModel: MainViewModel by viewModels{
            factory
        }

        viewModel.getCustomPosts(2, "id", "desc").observe(this) { response ->
            when (response) {
                is Resource.Loading -> {
                    Log.d(TAG, "onCreate: Loading")
                }
                is Resource.Success -> {
                    Log.d(TAG, "onCreate: ${response.data}")
                    response.data.let { myAdapter.setData(it) }
                }
                is Resource.Error -> {
                    Toast.makeText(applicationContext, "Connection error", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }

    }

    private fun setupRecyclerView(){
        binding.rvData.apply {
            adapter = myAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
            setHasFixedSize(true)
        }

        myAdapter.setOnITemClickCallback(object : MyAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Post) {
                val i = Intent(this@MainActivity, DetailActivity::class.java)
                i.putExtra(DATA_TAG, data)
                startActivity(i)
            }
        })
    }

    companion object {
        const val TAG = "Response::::::"
        const val DATA_TAG = "data"
    }
}