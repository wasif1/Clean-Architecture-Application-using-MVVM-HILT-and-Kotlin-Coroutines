package com.example.dubizzletest.di

import com.example.dubizzletest.BuildConfig
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

    /**
     * BASE URL FOR
     * API CALLS
     */
    @Provides
    fun providesBaseUrl(): String {
        return BuildConfig.BASE_URL
    }

    /**
     * HttpLoggingInterceptor
     * FOR RETROFIT CLIENT
     */
    @Provides
    fun providesLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    /**
     * OkHttpClient BUILDER INITIALISE
     * AND SET THE HttpLoggingInterceptor
     */
    @Provides
    fun providesOkHttpClient(
        logger: HttpLoggingInterceptor,
    ): OkHttpClient {
        val okHttpClient = OkHttpClient.Builder()
        okHttpClient.addInterceptor(logger)
        return okHttpClient.build()
    }

    /**
     * GsonConverterFactory FOR
     * PARSING API RESPONSE
     */
    @Provides
    fun providesConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    /**
     * Retrofit API CLIENT
     * INTEGRATION FOR API CALLS
     */
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

    /**
     * RETROFIT BIND WITH
     * API SERVICES
     */
    @Provides
    fun providesAPIService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }
}