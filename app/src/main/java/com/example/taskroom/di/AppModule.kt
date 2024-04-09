package com.example.taskroom.di

import com.example.taskroom.data.remote.api.authApi
import com.example.taskroom.data.remote.api.participantApi
import com.example.taskroom.data.remote.api.projectApi
import com.example.taskroom.data.remote.api.taskApi
import com.example.taskroom.data.remote.api.userApi
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule{
    @Provides
    @Singleton
    fun providesMoshi(): Moshi {
        return Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }
    val URL = "http://gdp-web-api.somee.com/"
    @Provides
    @Singleton
    fun provideProjectApi(moshi : Moshi): projectApi{
        return Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(projectApi::class.java)
    }
    @Provides
    @Singleton
    fun provideAuthApi(moshi : Moshi): authApi{
        return Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(authApi::class.java)
    }
    @Provides
    @Singleton
    fun provideTaskApi(moshi : Moshi): taskApi {
        return Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(taskApi::class.java)
    }
    @Provides
    @Singleton
    fun provideParticipantApi(moshi : Moshi): participantApi{
        return Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(participantApi::class.java)
    }
    @Provides
    @Singleton
    fun provideUserApi(moshi : Moshi): userApi{
        return Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(userApi::class.java)
    }


}
