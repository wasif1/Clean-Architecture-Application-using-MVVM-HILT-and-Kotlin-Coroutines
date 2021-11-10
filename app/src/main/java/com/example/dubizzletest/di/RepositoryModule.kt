package com.example.dubizzletest.di

import com.example.dubizzletest.repository.ListRepository
import com.example.dubizzletest.network.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@InstallIn(ActivityRetainedComponent::class)
@Module
object RepositoryModule {

    /**
     * Repositories
     */
    @Provides
    fun providesRepo(apiService: ApiService): ListRepository {
        return ListRepository(apiService = apiService)
    }
}