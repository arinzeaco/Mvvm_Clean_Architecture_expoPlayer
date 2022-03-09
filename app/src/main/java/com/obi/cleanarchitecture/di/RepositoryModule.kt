package com.obi.cleanarchitecture.di


import com.obi.cleanarchitecture.repository.VideoRepositoryImpl
import com.obi.cleanarchitecture.repository.dataSource.VideoDataSource
import com.obi.cleanarchitecture.domain.repository.VideoRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun provideNewsRepository(
        videoDataSource: VideoDataSource,
    ): VideoRepository {
        return VideoRepositoryImpl(
            videoDataSource
        )
    }

}














