package com.example.betsim.di

import com.example.betsim.data.remote.BetSimApi
import com.example.betsim.repository.BetSimRepository
import com.example.betsim.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideBetSimRepository(
        api : BetSimApi
    ) = BetSimRepository(api)

    @Singleton
    @Provides
    fun provideBetSimApi(): BetSimApi{
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(BetSimApi::class.java)
    }

}