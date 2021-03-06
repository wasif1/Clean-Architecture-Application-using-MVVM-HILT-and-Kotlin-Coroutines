package com.example.dubizzletest.di

import com.example.dubizzletest.repository.ListRepository
import com.example.dubizzletest.usecase.ListUsecase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@InstallIn(ActivityRetainedComponent::class)
@Module
object UsecaseModule {

    /**
     * UseCases
     */
    @Provides
    fun providesDataUsecase(repository: ListRepository): ListUsecase {
        return ListUsecase(repository)
    }
}