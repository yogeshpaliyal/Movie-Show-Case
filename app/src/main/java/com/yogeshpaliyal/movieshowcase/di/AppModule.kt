package com.yogeshpaliyal.movieshowcase.di

import com.yogeshpaliyal.movieshowcase.data.network.ApiInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Singleton
    @Provides
    fun getRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun getOkHttp(): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor {
            val newUrl = it.request().url().newBuilder()
                .addQueryParameter("api_key", "062571f50fde1ee1dd8902d38d0d9e8f").build()

            it.proceed(it.request().newBuilder().url(newUrl).build())
        }.build()
    }

    @Singleton
    @Provides
    fun getApiInterface(retrofit: Retrofit): ApiInterface{
        return retrofit.create(ApiInterface::class.java)
    }

}