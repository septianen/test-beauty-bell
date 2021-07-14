package com.example.beautybell.data.network

import android.content.Context
import com.example.beautybell.data.constant.Url
import com.readystatesoftware.chuck.ChuckInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiService {
    fun buildRetrofit(context: Context?): BeautyBellApi {
        val chuck = ChuckInterceptor(context)
        val okHttpClient = OkHttpClient.Builder()
            .readTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)
            .retryOnConnectionFailure(false)
            .addInterceptor(chuck)
            .build()
        val retrofit = Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(Url.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
        return retrofit.create(BeautyBellApi::class.java)
    }
}