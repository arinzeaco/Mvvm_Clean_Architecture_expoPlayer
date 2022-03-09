package com.obi.cleanarchitecture.domain.usecase

import com.obi.cleanarchitecture.VideoResponse
import com.obi.cleanarchitecture.util.Resource
import com.obi.cleanarchitecture.domain.repository.VideoRepository

class GetVideoUseCase(private val newsRepository: VideoRepository) {

    suspend fun execute(page : Int, per_page : Int): Resource<VideoResponse> {
        return newsRepository.getVidoes(page,per_page)
    }
}