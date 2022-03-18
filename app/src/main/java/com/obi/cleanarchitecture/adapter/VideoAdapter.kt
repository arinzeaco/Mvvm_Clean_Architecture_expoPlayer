package com.obi.cleanarchitecture.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
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

            binding.tvUserHandle.text = article.height.toString()
            mPlayer = ExoPlayer.Builder(context!!).build()

            binding.playerView.player = mPlayer
            mPlayer!!.playWhenReady = true
            Glide.with(binding.ivMediaCoverImage.context).
            load(article.image).
            into(binding.ivMediaCoverImage)
            mPlayer!!.setMediaSource(AppUtils().buildMediaSource(article.video_files[0].link))

            mPlayer!!.addListener(object : Player.Listener { // player listener

                override fun onPlayerStateChanged(playWhenReady: Boolean, playbackState: Int) {
                    when (playbackState) { // check player play back state
                        Player.STATE_READY -> {
                            Log.i("TAG_Adapter","Ready")
                            Glide.with(binding.ivMediaCoverImage.context).
                            load(article.image).
                            into(binding.ivMediaCoverImage)
                        }
                        Player.STATE_ENDED -> {
                            binding.ivMediaCoverImage.visibility= View.VISIBLE
                            Log.i("TAG_Adapter","Video has ended")
                        }
                        Player.STATE_BUFFERING ->{
                            Log.i("TAG_Adapter","Video has ended")
                        }
                        Player.STATE_IDLE -> {
                            Log.i("TAG_Adapter","Video has ended")

                        }
                        else -> {
                        }
                    }
                }
            })

            binding.ivMediaCoverImage.setOnClickListener {
                binding.ivMediaCoverImage.visibility= View.GONE
                mPlayer!!.prepare()
//                onItemClickListener?.let {
//
//
//                }
            }

        }
    }

    private var onItemClickListener :((VideoDataResponse)->Unit)?=null

    fun setOnItemClickListener(listener : (VideoDataResponse)->Unit){
        onItemClickListener = listener
    }

}






