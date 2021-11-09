package com.example.dubizzletest.di

import com.example.dubizzletest.network.ApiService
import com.example.dubizzletest.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {
    @Provides
    fun providesBaseUrl(): String {
        return Constants.BASE_URL
    }

    @Provides
    fun providesLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Provides
    fun providesOkHttpClient(
        logger: HttpLoggingInterceptor,
    ): OkHttpClient {
        val okHttpClient = OkHttpClient.Builder()
        okHttpClient.addInterceptor(logger)
        return okHttpClient.build()
    }

    @Provides
    fun providesConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @Provides
    fun providesRetrofit(
        baseUrl: String,
        converter: GsonConverterFactory,
        client: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(converter)
            .client(client)
            .build()
    }

    @Provides
    fun providesAPIService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }
}