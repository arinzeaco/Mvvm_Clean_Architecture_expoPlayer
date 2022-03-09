package com.obi.cleanarchitecture.repository

import com.obi.cleanarchitecture.VideoResponse
import com.obi.cleanarchitecture.repository.dataSource.VideoDataSource
import com.obi.cleanarchitecture.domain.repository.VideoRepository
import com.obi.cleanarchitecture.util.Resource
import retrofit2.Response

class VideoRepositoryImpl(
    private val videoDataSource: VideoDataSource,
): VideoRepository {

    override suspend fun getVidoes(page: Int, per_page: Int): Resource<VideoResponse> {
        return responseToResource(videoDataSource.getVideos(page,per_page))
    }

//    override suspend fun getSearchedNews(
//        country: String,
//        searchQuery: String,
//        page: Int
//    ): Resource<VideoResponse> {
//        return responseToResource(
//            videoDataSource.getSearchedNews(country,searchQuery,page)
//        )
//    }

    private fun responseToResource(response:Response<VideoResponse>):Resource<VideoResponse>{
        if(response.isSuccessful){
            response.body()?.let {result->
                return Resource.Success(result)
            }
        }
        return Resource.Error(response.message())
    }



//    override suspend fun saveNews(article: Article) {
//        newsLocalDataSource.saveArticleToDB(article)
//    }
//
//    override suspend fun deleteNews(article: Article) {
//        newsLocalDataSource.deleteArticlesFromDB(article)
//    }
//
//    override fun getSavedNews(): Flow<List<Article>> {
//        return newsLocalDataSource.getSavedArticles()
//    }
}