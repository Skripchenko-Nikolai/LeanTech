package com.pirksni.leantech.di.module

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.pirksni.leantech.BuildConfig
import com.pirksni.leantech.data.api.LeanTechServiceApi
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideHttpClient(context: Context): OkHttpClient {
        val builder = OkHttpClient.Builder()
            .readTimeout(TIMEOUT, TimeUnit.SECONDS)
            .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
        if (BuildConfig.DEBUG) {
            builder.addInterceptor(ChuckerInterceptor.Builder(context).build())
            builder.addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        }
        return builder.build()
    }

    @Provides
    @Singleton
    fun provideApiService(client: OkHttpClient): LeanTechServiceApi =
        Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create())
            .baseUrl(URL)
            .client(client)
            .build()
            .create(LeanTechServiceApi::class.java)


    companion object {
        private const val URL = "https://sheets.googleapis.com/v4/spreadsheets/"
        private const val TIMEOUT = 100L
    }
}
