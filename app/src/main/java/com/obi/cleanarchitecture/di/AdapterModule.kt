package com.obi.cleanarchitecture.di

import com.obi.cleanarchitecture.adapter.VideoAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AdapterModule {
   @Singleton
   @Provides
   fun provideNewsAdapter():VideoAdapter{
       return VideoAdapter()
   }
}