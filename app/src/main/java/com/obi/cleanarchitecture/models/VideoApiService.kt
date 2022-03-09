package com.obi.cleanarchitecture.models

import com.obi.cleanarchitecture.BuildConfig
import com.obi.cleanarchitecture.VideoResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface VideoApiService {
  @GET("search?query=nature")
  suspend fun getVideos(
      @Query("page")
      page:Int,
      @Query("per_page")
      per_page:Int,

  ): Response<VideoResponse>
}