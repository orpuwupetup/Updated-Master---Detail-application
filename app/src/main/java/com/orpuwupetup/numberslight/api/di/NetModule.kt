package com.orpuwupetup.numberslight.api.di

import com.orpuwupetup.numberslight.BuildConfig
import com.orpuwupetup.numberslight.api.NumbersLightService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
object NetModule {

    // provide service used for making HTTP requests throughout whole app via dependency injection
    @Provides
    @Singleton
    @JvmStatic
    internal fun provideBasicApiService(): NumbersLightService {

        return Retrofit.Builder()
            .baseUrl(BuildConfig.SERVER_BASE_URL)
            .client(createOkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build().create(NumbersLightService::class.java)
    }

    // Create okHttpClient which will make internet calls
    private fun createOkHttpClient(): OkHttpClient {

        return OkHttpClient.Builder().apply {
            if (BuildConfig.DEBUG)
                addInterceptor(
                    HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                })
        }
            .connectTimeout(1, TimeUnit.MINUTES)
            .writeTimeout(1, TimeUnit.MINUTES)
            .readTimeout(1, TimeUnit.MINUTES)
            .build()
    }
}