package com.example.remote.di.modules

import androidx.paging.PagingSource
import com.example.remote.data.ApiConstants
import com.example.remote.data.OnwaveApi
import com.example.remote.data.PlaylistsPagingSource
import com.example.remote.data.repositories.OnwaveRepositoriesImpl
import com.example.remote.data.repositories.OnwaveRepository
import com.example.remote.models.ui.PlaylistUi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class RemoteModule {

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BASIC
            })
            .build()

    @Singleton
    @Provides
    fun provideRetrofit(client: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .client(client)
            .baseUrl(ApiConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Singleton
    @Provides
    fun provideOnwaveApi(retrofit: Retrofit): OnwaveApi =
        retrofit.create(OnwaveApi::class.java)

    @Singleton
    @Provides
    fun provideRepository(onwaveApi: OnwaveApi): OnwaveRepository =
        OnwaveRepositoriesImpl(onwaveApi)

    @Singleton
    @Provides
    fun providePagingSource(repository: OnwaveRepository): PagingSource<Int, PlaylistUi> =
        PlaylistsPagingSource(repository)

}