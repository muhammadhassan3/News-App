package com.muhammhassan.newsapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.muhammhassan.domain.model.NewsModel
import com.muhammhassan.newsapp.databinding.ItemLayoutBinding

class NewsAdapter(private val data: List<NewsModel>) :
    RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    class ViewHolder(binding: ItemLayoutBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(item: NewsModel){

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }
}