package com.obi.cleanarchitecture.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.obi.cleanarchitecture.VideoDataResponse
import com.obi.cleanarchitecture.databinding.NewsListItemBinding
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.Player
import com.obi.cleanarchitecture.util.AppUtils


class VideoAdapter:RecyclerView.Adapter<VideoAdapter.NewsViewHolder>(), Player.Listener  {

    private var mPlayer: ExoPlayer? = null


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
        return NewsViewHolder(binding,parent.context)
    }



    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val article = differ.currentList[position]
        holder.bind(article)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    inner class NewsViewHolder(
        val binding:NewsListItemBinding, var context: Context?, ):
        RecyclerView.ViewHolder(binding.root){
        fun bind(article: VideoDataResponse){
            Log.i("MYTAG","came here ${article.image}")

            binding.tvUserHandle.text = article.height.toString()
            binding.playerView

            mPlayer = ExoPlayer.Builder(context!!).build()

            binding.playerView.player = mPlayer
            mPlayer!!.playWhenReady = true

            mPlayer!!.setMediaSource(AppUtils().buildMediaSource(article.video_files[1].link))


            mPlayer!!.prepare()

            binding.root.setOnClickListener {
                onItemClickListener?.let {
                    it(article)
                }
            }
        }
    }

    private var onItemClickListener :((VideoDataResponse)->Unit)?=null


}






