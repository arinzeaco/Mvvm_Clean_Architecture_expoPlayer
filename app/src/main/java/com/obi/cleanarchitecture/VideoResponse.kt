package com.obi.cleanarchitecture


import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class VideoResponse(
	@SerializedName("videos")
	val videoDataResponses: List<VideoDataResponse>,
	@SerializedName("totalResults")
	val totalResults: Int
): Serializable




data class VideoDataResponse (
	@PrimaryKey(autoGenerate = true)
	val id : Int? = null,
	@SerializedName("height") val height : Int?,
	@SerializedName("image") val image : String?,
	@SerializedName("url") val url : String,
	@SerializedName("video_files") val video_files : List<VideoFiles>,
): Serializable
data class VideoFiles (
	@PrimaryKey(autoGenerate = true)
	val id : Int? = null,
	@SerializedName("link") val link : String,
): Serializable
