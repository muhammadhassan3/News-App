package com.muhammhassan.bookmark.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.muhammhassan.bookmark.databinding.ActivityBookmarkBinding
import com.muhammhassan.bookmark.di.Module
import com.muhammhassan.bookmark.ui.viewmodel.BookmarkViewModel
import com.muhammhassan.newsapp.R
import com.muhammhassan.newsapp.adapter.NewsAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class BookmarkActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBookmarkBinding
    private val viewModel: BookmarkViewModel by viewModel()
    private lateinit var newsAdapter: NewsAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBookmarkBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadKoinModules(Module.bookmarkViewModelModule)

        binding.apply {
            setSupportActionBar(toolbar)
            supportActionBar?.apply{
                setHomeAsUpIndicator(R.drawable.baseline_arrow_back_ios_24)
                setDisplayHomeAsUpEnabled(true)
            }
            rvList.apply {
                layoutManager = LinearLayoutManager(this@BookmarkActivity)
                newsAdapter = NewsAdapter{
                    Toast.makeText(this@BookmarkActivity, "Item Selected", Toast.LENGTH_SHORT).show()
                }
                adapter = newsAdapter
            }
        }
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.data.observe(this){
            println(it)
            newsAdapter.setData(it)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }
}