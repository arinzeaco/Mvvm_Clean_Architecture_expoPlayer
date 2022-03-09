package com.obi.cleanarchitecture.di

import com.obi.cleanarchitecture.models.VideoApiService
import com.obi.cleanarchitecture.repository.dataSource.VideoDataSource
import com.obi.cleanarchitecture.repository.dataSourceImpl.VideoDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class RemoteDataModule {

    @Singleton
    @Provides
    fun provideVideoDataSource(
        videoApiService: VideoApiService
    ):VideoDataSource{
       return VideoDataSourceImpl(videoApiService)
    }

}












