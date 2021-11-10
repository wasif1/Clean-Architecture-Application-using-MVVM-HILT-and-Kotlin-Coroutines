package com.example.dubizzletest.hilt

import com.example.dubizzletest.api.FakeApiService
import com.example.dubizzletest.di.NetworkModule
import com.example.dubizzletest.network.ApiService
import dagger.Binds
import dagger.Module
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

@Module
@TestInstallIn(components = [SingletonComponent::class], replaces = [NetworkModule::class])
abstract class FakeApiServiceModule {

    @Binds
    @Singleton
    abstract fun providesApiServices(fakeApiService: FakeApiService): ApiService
}