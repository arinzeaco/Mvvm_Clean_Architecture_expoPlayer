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



//data class VideoResponseb (
//	@SerializedName("videos")
//	val data: List<VideoDataResponse>,
//	@SerializedName("status")
//	val status: String,
//	@SerializedName("totalResults")
//	val totalResults: Int
//
//
//)
//
//
//data class VideoDataResponse(
//	@SerializedName("id") val id : Int?,
//	@SerializedName("width") val width : Int?,
//	@SerializedName("height") val height : Int,
//	@SerializedName("duration") val duration : Int,
//	@SerializedName("full_res") val full_res : String,
//	@SerializedName("image") val image : String,
//	@SerializedName("avg_color") val avg_color : String,
//	@SerializedName("video_files") val video_files : String,
//):Serializable

data class VideoDataResponse (
	@PrimaryKey(autoGenerate = true)
	val id : Int? = null,
	@SerializedName("height") val height : Int?,
//	@SerializedName("duration") val duration : Int,
//	@SerializedName("full_res") val full_res : String,
	@SerializedName("image") val image : String?,
//	@SerializedName("url") val url : String,
): Serializable
