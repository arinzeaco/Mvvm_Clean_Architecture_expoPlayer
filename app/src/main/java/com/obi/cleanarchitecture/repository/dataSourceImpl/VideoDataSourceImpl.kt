package com.obi.cleanarchitecture.repository.dataSourceImpl

import com.obi.cleanarchitecture.VideoResponse
import com.obi.cleanarchitecture.models.VideoApiService
import com.obi.cleanarchitecture.repository.dataSource.VideoDataSource
import retrofit2.Response

class VideoDataSourceImpl(
        private val videoApiService: VideoApiService
): VideoDataSource {
    override suspend fun getVideos(page : Int, per_page : Int): Response<VideoResponse> {
          return videoApiService.getVideos(page,per_page)
    }

//    override suspend fun getSearchedNews(
//        country: String,
//        searchQuery: String,
//        page: Int
//    ): Response<VideoResponse> {
//        return newsAPIService.getSearchedTopHeadlines(country,searchQuery,page)
//    }
}