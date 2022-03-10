package com.example.retrofitkotlincoroutines

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitkotlincoroutines.adapter.MyAdapter
import com.example.retrofitkotlincoroutines.databinding.ActivityMainBinding
import com.example.retrofitkotlincoroutines.models.Post
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*

@AndroidEntryPoint
class  MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    private val myAdapter by lazy {
        MyAdapter()
    }
    private var limit = 0
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getPost(viewModel)
        setupRecyclerView(viewModel)
    }

    private fun getPost(viewModel: MainViewModel) {
        viewModel.getCustomPosts(2, "id", "desc").observe(this) { response ->
            when (response) {
                is Resource.Loading -> {
                    Log.d(TAG, "onCreate: Loading")
                }
                is Resource.Success -> {
                    Log.d(TAG, "onCreate: ${response.data}")
                    if(limit < response.data.size ){
                        limit += 6
                    }
                    myAdapter.setData(response.data.take(limit) as ArrayList<Post>)
                    binding.progressBar.visibility = View.GONE
                }
                is Resource.Error -> {
                    Toast.makeText(applicationContext, "Connection error", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
    }

    private fun setupRecyclerView(viewModel: MainViewModel) {
        binding.rvData.apply {
            adapter = myAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
            setHasFixedSize(true)
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    val lnManager =
                        LinearLayoutManager::class.java.cast(recyclerView.layoutManager)
                    val lastVisibleItem = lnManager.findLastCompletelyVisibleItemPosition()
                    val total = myAdapter.itemCount

                    if (lastVisibleItem+1 >= total) {
                        binding.progressBar.visibility = View.VISIBLE
                        CoroutineScope(Dispatchers.Main).launch {
                            delay(3000)
                            getPost(viewModel)
                        }
                    }

                    Log.d(TAG, "lastVisible: $lastVisibleItem total: $total")
                }
            })

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