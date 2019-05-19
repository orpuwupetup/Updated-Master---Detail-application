package com.orpuwupetup.numberslight.api.di

import com.orpuwupetup.numberslight.BuildConfig
import com.orpuwupetup.numberslight.api.NumbersLightService
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


// TODO think about adding http logging interceptor to retrofit, and about moshi implementation
@Module
object NetModule {

    // provide service used for making HTTP requests throughout whole app via dependency injection
    @Provides
    @Singleton
    @JvmStatic
    internal fun provideBasicApiService(): NumbersLightService {

//        val moshi = Moshi.Builder()
//            .add(KotlinJsonAdapterFactory())
//            .build()

        return Retrofit.Builder()

            .baseUrl(BuildConfig.SERVER_BASE_URL)
            .client(createOkHttpClient())
            // .addConverterFactory(MoshiConverterFactory.create(moshi))
            // .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build().create(NumbersLightService::class.java)
    }

    // Create okHttpClient which will make internet calls
    private fun createOkHttpClient(): OkHttpClient {
//        val interceptor = HttpLoggingInterceptor().apply {
//            level = HttpLoggingInterceptor.Level.BODY
//        }

        return OkHttpClient.Builder()
//            .addInterceptor(interceptor)
            .connectTimeout(1, TimeUnit.MINUTES)
            .writeTimeout(1, TimeUnit.MINUTES)
            .readTimeout(1, TimeUnit.MINUTES)
            .build()
    }
}