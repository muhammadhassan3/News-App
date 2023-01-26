package com.muhammhassan.domain.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.muhammhassan.domain.R
import com.muhammhassan.domain.databinding.ItemLayoutBinding
import com.muhammhassan.domain.model.NewsModel
import com.muhammhassan.domain.utils.Utils.loadImage

class NewsAdapter(private val onItemClick: (item: NewsModel) -> Unit) :
    RecyclerView.Adapter<NewsAdapter.ViewHolder>() {
    private val data = arrayListOf<NewsModel>()

    fun setData(newData: List<NewsModel>){
        val diffCallback = NewsDiffUtil(data, newData)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        data.clear()
        data.addAll(newData)
        diffResult.dispatchUpdatesTo(this)
    }

    class NewsDiffUtil(private val oldData: List<NewsModel>, private val newData: List<NewsModel>): DiffUtil.Callback(){
        override fun getOldListSize(): Int {
            return oldData.size
        }

        override fun getNewListSize(): Int {
            return newData.size
        }

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldData[oldItemPosition].title == newData[newItemPosition].title
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldData[oldItemPosition] == newData[newItemPosition]
        }

    }
    class ViewHolder(val binding: ItemLayoutBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        with(holder){
            binding.apply {
                tvDesc.text = item.desc
                tvTitle.text = item.title
                if(item.image != null){
                    item.image.let {
                        imgHeader.loadImage(it)
                    }
                }else imgHeader.loadImage(R.drawable.baseline_image_not_supported_24)
            }
            itemView.setOnClickListener {
                onItemClick.invoke(item)
            }
        }
    }
}