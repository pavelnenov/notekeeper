package com.example.notekeeper.di

import com.example.notekeeper.data.api.CoursesApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class NetworkProvider {

    @Provides
    fun okHttpClientProvider() : OkHttpClient = OkHttpClient.Builder().apply {
        readTimeout(5000, TimeUnit.MILLISECONDS)
        writeTimeout(5000, TimeUnit.MILLISECONDS)
        connectTimeout(5000, TimeUnit.MILLISECONDS)
    }.build()

    @Provides
    fun retrofitProvider(okHttpClient : OkHttpClient) : Retrofit = Retrofit.Builder()
        .addConverterFactory(JacksonConverterFactory.create())
        .baseUrl("http://10.0.2.2:8080")
        .client(okHttpClient)
        .build()

    @Provides
    fun provideCoursesApi(retrofit: Retrofit) : CoursesApi = retrofit.create(CoursesApi::class.java)

//    @Singleton
//    @Provides
//    fun provideCoursesApi() : CoursesApi {
//        val okHttpClient = OkHttpClient.Builder().apply {
//            readTimeout(5000, TimeUnit.MILLISECONDS)
//            writeTimeout(5000, TimeUnit.MILLISECONDS)
//            connectTimeout(5000, TimeUnit.MILLISECONDS)
//        }.build()
//        return Retrofit.Builder()
//            .addConverterFactory(JacksonConverterFactory.create())
//            .baseUrl("http://10.0.2.2:8080")
//            .client(okHttpClient)
//            .build().create(CoursesApi::class.java)
//    }
}