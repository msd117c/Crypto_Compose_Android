package com.msd117.cryptocompose.di

import com.msd117.cryptocompose.BuildConfig
import com.msd117.cryptocompose.utils.ApiConstants.baseUrl
import com.msd117.cryptocompose.utils.ApiConstants.headerApiKeyParameterName
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val httpLoggingInterceptor = HttpLoggingInterceptor().also { logging ->
            logging.setLevel(HttpLoggingInterceptor.Level.BASIC)
        }
        val httpClientBuilder = OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor(Interceptor { chain ->
                val originalRequest = chain.request()

                val request = originalRequest.newBuilder()
                    .header(headerApiKeyParameterName, BuildConfig.API_KEY)
                    .method(originalRequest.method, originalRequest.body)
                    .build()

                chain.proceed(request)
            })
        return httpClientBuilder.build()
    }

    @Provides
    fun provideRetrofitClient(httpClient: OkHttpClient): Retrofit {
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        val retrofitBuilder = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(httpClient)

        return retrofitBuilder.build()
    }
}