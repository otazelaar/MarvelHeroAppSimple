package com.otaz.marvelheroappsimple.di

import android.content.Context
import com.otaz.marvelheroappsimple.network.MovieService
import com.otaz.marvelheroappsimple.utils.constants.Companion.API_BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideApplication(@ApplicationContext app: Context): BaseApplication {
        return app as BaseApplication
    }

    @Singleton
    @Provides
    fun provideRandomString(): String{
        return "Hey Look a random string!!!!!JGKDJL"
    }

//    @Singleton
//    @Provides
//    fun provideRepository(
//        apiService: ApiService
//    ): CharacterRepository {
//        return CharacterRepository(apiService)
//    }

    @Singleton
    @Provides
    fun provideApiClient(): MovieService {
        val client = OkHttpClient.Builder()
            .connectTimeout(100, TimeUnit.SECONDS)
            .readTimeout(100, TimeUnit.SECONDS).build();
        return Retrofit.Builder().baseUrl(API_BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MovieService::class.java)
    }

}