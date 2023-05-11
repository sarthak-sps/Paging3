package com.example.paging3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.example.paging3.apiService.MovieService
import com.example.paging3.apiService.RetrofitInstance
import com.example.paging3.databinding.ActivityMainBinding
import com.example.paging3.repository.MainRepository
import com.example.paging3.viewModel.MainViewModel
import com.example.paging3.viewModel.MainViewModelFactory
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
     var adapter =  MoviePagingAdapter()
    lateinit var viewModel: MainViewModel
    lateinit  var binding :ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val retrofitService = RetrofitInstance.getInstance().create(MovieService::class.java)
         val mainRepository= MainRepository(retrofitService)
         binding.recyclerview.adapter=adapter

        viewModel = ViewModelProvider(this, MainViewModelFactory(mainRepository)).get(MainViewModel::class.java)

        adapter.addLoadStateListener { loadState ->
            // show empty list
            if (loadState.refresh is LoadState.Loading ||
                loadState.append is LoadState.Loading)
                binding.progressbar.isVisible = true
            else {
                binding.progressbar.isVisible = false
                // If we have an error, show a toast
                val errorState = when {
                    loadState.append is LoadState.Error -> loadState.append as LoadState.Error
                    loadState.prepend is LoadState.Error ->  loadState.prepend as LoadState.Error
                    loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
                    else -> null
                }
                errorState?.let {
                    Toast.makeText(this, it.error.toString(), Toast.LENGTH_LONG).show()
                }

            }
        }

    lifecycleScope.launch{
        viewModel.getMovieList().observe(this@MainActivity) {
            it?.let {
                adapter.submitData(lifecycle, it)
            }
        }
    }

    }


}