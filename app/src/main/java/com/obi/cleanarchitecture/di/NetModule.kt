package com.obi.cleanarchitecture.di

import com.obi.cleanarchitecture.models.VideoApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetModule {
    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
         return Retrofit.Builder()
             .addConverterFactory(GsonConverterFactory.create())
             .client(client)
             .baseUrl("https://api.pexels.com/videos/")
             .build()
    }

    @Singleton
    @Provides
    fun provideVideoService(retrofit: Retrofit): VideoApiService {
        return retrofit.create(VideoApiService::class.java)
    }



    private fun getHeaderInterceptor(): Interceptor {
        return Interceptor { chain ->
            var request = chain.request()

            request = request.newBuilder()
                 //get your own authorization key and put it here
                .addHeader("Authorization", "")
                .addHeader("Content-type", "application/json")
                .build()

            chain.proceed(request)
        }
    }
    val interceptor = HttpLoggingInterceptor().apply {
        this.level = HttpLoggingInterceptor.Level.BODY
    }
    val client = OkHttpClient.Builder().apply {
        this.addInterceptor(interceptor)
            .connectTimeout(30,TimeUnit.SECONDS)
            .readTimeout(20,TimeUnit.SECONDS)
            .writeTimeout(25,TimeUnit.SECONDS)
            .addNetworkInterceptor(interceptor)
            .addInterceptor(getHeaderInterceptor())
    }.build()

}













