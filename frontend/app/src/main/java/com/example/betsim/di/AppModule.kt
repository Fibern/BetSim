package com.example.betsim.di

import android.content.Context
import com.example.betsim.data.local.OfferHolder
import com.example.betsim.data.local.SecurePreferencesHelper
import com.example.betsim.data.remote.BetSimApi
import com.example.betsim.repository.BetSimRepository
import com.example.betsim.util.Constants.BASE_URL
import com.example.betsim.util.LocalDateTimeAdapter
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.time.LocalDateTime
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
        val gson = GsonBuilder()
            .registerTypeAdapter(LocalDateTime::class.java, LocalDateTimeAdapter().nullSafe())
            .create()

        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl(BASE_URL)
            .build()
            .create(BetSimApi::class.java)
    }

    @Singleton
    @Provides
    fun provideSecurePreferencesHelper(@ApplicationContext appContext: Context): SecurePreferencesHelper{
        return SecurePreferencesHelper(appContext)
    }

    @Singleton
    @Provides
    fun provideOfferHolder(): OfferHolder{
        return OfferHolder()
    }

}