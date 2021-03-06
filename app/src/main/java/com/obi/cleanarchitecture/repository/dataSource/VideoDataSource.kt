package com.obi.cleanarchitecture.repository.dataSource

import com.obi.cleanarchitecture.VideoResponse
import retrofit2.Response

interface VideoDataSource {
    suspend fun getVideos(page : Int, per_page : Int):Response<VideoResponse>
}

