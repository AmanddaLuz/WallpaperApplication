package com.amandaluz.wallpaperapplication.framework.di

import com.amandaluz.wallpaperapplication.BuildConfig
import com.amandaluz.wallpaperapplication.framework.network.api.WallpapersAPI
import com.amandaluz.wallpaperapplication.framework.network.interceptor.AuthorizationInterceptor
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val TIMEOUT_SECONDS = 15L

    @Provides
    fun providerLoginInterceptor() : HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        setLevel(
            if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.NONE
            }
        )
    }

    @Provides
    fun provideAuthorizationInterceptor() : AuthorizationInterceptor =
        AuthorizationInterceptor(BuildConfig.PRIVATE_KEY)

    @Provides
    fun provideOHTTPClient(
        loginInterceptor : HttpLoggingInterceptor ,
        authorizationInterceptor : AuthorizationInterceptor
    ) : OkHttpClient = OkHttpClient.Builder()
        .readTimeout(TIMEOUT_SECONDS , TimeUnit.SECONDS)
        .connectTimeout(TIMEOUT_SECONDS , TimeUnit.SECONDS)
        .writeTimeout(TIMEOUT_SECONDS , TimeUnit.SECONDS)
        .addInterceptor(loginInterceptor)
        .addInterceptor(authorizationInterceptor)
        .build()

    @Provides
    fun provideGson(): Gson = GsonBuilder().setLenient().create()

    @Provides
    fun provideConverterFactory(gson: Gson):GsonConverterFactory = GsonConverterFactory.create(gson)

    @Provides
    fun provideRetrofit(httpClient : OkHttpClient , converterFactory : GsonConverterFactory): WallpapersAPI = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .client(httpClient)
        .addConverterFactory(converterFactory)
        .build()
        .create(WallpapersAPI::class.java)
}