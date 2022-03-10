package com.example.retrofitkotlincoroutines

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.retrofitkotlincoroutines.MainActivity.Companion.DATA_TAG
import com.example.retrofitkotlincoroutines.databinding.ActivityDetailBinding
import com.example.retrofitkotlincoroutines.models.Post
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {

    lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val data = intent.getParcelableExtra<Post>(DATA_TAG)

        binding.tvUserId.text = data?.userId.toString()
        binding.tvId.text = data?.id.toString()
        binding.tvTitle.text = data?.title.toString()
        binding.tvBody.text = data?.body.toString()
    }
}