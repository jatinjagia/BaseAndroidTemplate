package com.baseandroidtemplate.network

import android.content.Context
import com.baseandroidtemplate.base.BuildConfig
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

private const val AUTH_TOKEN_HEADER = "auth_token_header"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory()).build()

private fun getInterceptor(): HttpLoggingInterceptor {
    val interceptor = HttpLoggingInterceptor()
    if (BuildConfig.DEBUG)
        interceptor.level = HttpLoggingInterceptor.Level.BODY
    return interceptor
}

private class OkHttpInterceptor(context: Context) : Interceptor {
    val appContext = context

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val requestHeader = request.newBuilder().addHeader(AUTH_TOKEN_HEADER, "authToken")
            .build()
        return chain.proceed(requestHeader)
    }
}


private fun getClient(context: Context): OkHttpClient {
    return OkHttpClient().newBuilder().addInterceptor(OkHttpInterceptor(context)).addInterceptor(
        getInterceptor()
    ).build()
}

private fun getBaseUrl(context: Context): String {
    return BuildConfig.BASE_URL
//    return if (BuildConfig.DEBUG) {
//        BuildConfig.BASE_URL
//    } else
//        BuildConfig.BASE_URL_RELEASE
}

fun retrofitClient(context: Context): Retrofit = Retrofit.Builder()
    .client(getClient(context))
    .addConverterFactory(MoshiConverterFactory.create(moshi).asLenient())
    .baseUrl(getBaseUrl(context))
    .build()