package com.example.retrofitkotlincoroutines

import android.icu.number.IntegerWidth
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.retrofitkotlincoroutines.Repository.Repository
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)

        val options: HashMap<String, String> = HashMap()
        options["_sort"] = "id"
        options["_order"] = "desc"

        button.setOnClickListener{
            val myNumber = etNumber.text.toString()
            viewModel.getCustomPost2(Integer.parseInt(myNumber), options)
            viewModel.myCustomPost2.observe(this, Observer { response ->
                if(response.isSuccessful){
                    tvTitle.text = response.body().toString()
                    response.body()?.forEach{
                        Log.d("Response", it.userId.toString())
                        Log.d("Response", it.id.toString())
                        Log.d("Response", it.title)
                        Log.d("Response", it.body)
                        Log.d("Response", "================")
                    }

                }else{
                    Log.d("Response", response.errorBody().toString())
                    tvTitle.text = response.code().toString()
                }


            })
        }
    }
}