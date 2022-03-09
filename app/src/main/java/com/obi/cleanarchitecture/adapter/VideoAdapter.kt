package com.obi.cleanarchitecture.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.obi.cleanarchitecture.VideoDataResponse
import com.obi.cleanarchitecture.databinding.NewsListItemBinding

class VideoAdapter:RecyclerView.Adapter<VideoAdapter.NewsViewHolder>() {


    private val callback = object : DiffUtil.ItemCallback<VideoDataResponse>(){
        override fun areItemsTheSame(oldItem: VideoDataResponse, newItem: VideoDataResponse): Boolean {
            return oldItem.image == newItem.image
        }

        override fun areContentsTheSame(oldItem: VideoDataResponse, newItem: VideoDataResponse): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this,callback)



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val binding = NewsListItemBinding
            .inflate(LayoutInflater.from(parent.context),parent,false)
        return NewsViewHolder(binding)
    }



    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val article = differ.currentList[position]
        holder.bind(article)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    inner class NewsViewHolder(
        val binding:NewsListItemBinding):
        RecyclerView.ViewHolder(binding.root){
        fun bind(article: VideoDataResponse){
            Log.i("MYTAG","came here ${article.image}")
            binding.tvTitle.text = article.image
            binding.tvDescription.text = article.image
            binding.tvPublishedAt.text = article.image
            binding.tvSource.text = article.image

//            Glide.with(binding.ivArticleImage.context).
//            load(article.image).
//            into(binding.ivArticleImage)

            binding.root.setOnClickListener {
                onItemClickListener?.let {
                    it(article)
                }
            }
        }
    }

    private var onItemClickListener :((VideoDataResponse)->Unit)?=null

    fun setOnItemClickListener(listener : (VideoDataResponse)->Unit){
        onItemClickListener = listener
    }


}






