package com.msd.network

import com.msd.network.ApiConstants.HEADER_API_KEY_PARAMETER_NAME
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object NetworkProvider {

    private fun buildOkHttpClient(apiKey: String): OkHttpClient {
        val httpLoggingInterceptor = HttpLoggingInterceptor().also { logging ->
            logging.setLevel(HttpLoggingInterceptor.Level.BASIC)
        }
        val httpClientBuilder = OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor(Interceptor { chain ->
                val originalRequest = chain.request()

                val request = originalRequest.newBuilder()
                    .header(HEADER_API_KEY_PARAMETER_NAME, apiKey)
                    .method(originalRequest.method, originalRequest.body)
                    .build()

                chain.proceed(request)
            })
        return httpClientBuilder.build()
    }

    fun buildRetrofitClient(apiKey: String): Retrofit {
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        val retrofitBuilder = Retrofit.Builder()
            .baseUrl(ApiConstants.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(buildOkHttpClient(apiKey))

        return retrofitBuilder.build()
    }
}