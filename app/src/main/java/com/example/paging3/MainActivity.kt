package com.example.paging3

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.paging3.Adapter.DogsAdapter
import com.example.paging3.Adapter.LoadingStateAdapter
import com.example.paging3.ViewModel.MainViewModel
import com.example.paging3.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModels()

    @Inject
    lateinit var dogAdapter: DogsAdapter

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setContentView(binding.root)

        initRecycleView()
        lifecycleScope.launchWhenStarted {
            mainViewModel.getAllDogsModel().collectLatest {
                binding.apply {
                    recyclerview.visibility = View.VISIBLE
                    progressBar.visibility = View.GONE
                }
                dogAdapter.submitData(it)
            }
        }
        binding.swipeRefresh.setOnRefreshListener {
            dogAdapter.refresh()
            binding.swipeRefresh.setRefreshing(false)
        }

    }

    private fun initRecycleView() {
        binding.apply {
            recyclerview.apply {
                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(this@MainActivity)
                adapter = dogAdapter.withLoadStateHeaderAndFooter(
                    header = LoadingStateAdapter { dogAdapter.retry() },
                    footer = LoadingStateAdapter { dogAdapter.retry() }
                )
            }

        }

    }
}