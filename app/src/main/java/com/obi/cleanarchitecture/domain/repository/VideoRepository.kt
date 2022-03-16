package com.obi.cleanarchitecture.domain.repository

import com.obi.cleanarchitecture.VideoResponse
import com.obi.cleanarchitecture.util.Resource

interface VideoRepository{

      suspend fun getVidoes(page : Int, per_page : Int): Resource<VideoResponse>
}