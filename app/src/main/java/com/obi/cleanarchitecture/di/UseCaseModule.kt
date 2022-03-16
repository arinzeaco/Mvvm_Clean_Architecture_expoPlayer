package com.obi.cleanarchitecture.di


import com.obi.cleanarchitecture.domain.repository.VideoRepository

import com.obi.cleanarchitecture.domain.usecase.GetVideoUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {
   @Singleton
   @Provides
   fun provideGetNewsheadLinesUseCase(
      videoRepository: VideoRepository
   ): GetVideoUseCase {
      return GetVideoUseCase(videoRepository)
   }

}


















