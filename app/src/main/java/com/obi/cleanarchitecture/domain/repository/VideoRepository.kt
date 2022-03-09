package com.obi.cleanarchitecture.domain.repository

import com.obi.cleanarchitecture.VideoResponse
import com.obi.cleanarchitecture.util.Resource

interface VideoRepository{

      suspend fun getVidoes(page : Int, per_page : Int): Resource<VideoResponse>
//      suspend fun getSearchedNews(country: String,searchQuery:String,page: Int) : Resource<APIResponse>
//      suspend fun saveNews(article: Article)
//      suspend fun deleteNews(article: Article)
//      fun getSavedNews(): Flow<List<Article>>
}