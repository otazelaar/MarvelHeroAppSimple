package com.otaz.marvelheroappsimple.data.di

import com.otaz.marvelheroappsimple.common.Constants.Companion.BASE_URL
import com.otaz.marvelheroappsimple.data.remote.MarvelApi
import com.otaz.marvelheroappsimple.data.repository.CharacterRepositoryImpl
import com.otaz.marvelheroappsimple.domain.repository.CharacterRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module@InstallIn(SingletonComponent::class)
object AppModule {

    // Provides API dependency
    @Provides
    @Singleton
    fun provideMarvelApi(): MarvelApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MarvelApi::class.java)
    }

    // Provides repository dependency
    @Provides
    @Singleton
    fun provideCharacterRepository(api: MarvelApi): CharacterRepository {
        return CharacterRepositoryImpl(api)
    }

}