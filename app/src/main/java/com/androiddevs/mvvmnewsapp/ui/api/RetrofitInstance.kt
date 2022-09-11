package com.androiddevs.mvvmnewsapp.ui.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import util.Constans.Companion.BASE_URL

class RetrofitInstance {
    companion object {

        //by lazy sadece retrofit değişkeni çağrıldığında kullanıldı
        private val retrofit by lazy {

            //Çağrıları yakalamak için interceptor kullanıldı
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY
            val client = OkHttpClient.Builder()
                .addInterceptor(logging)
                .build()

            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
        }

        val api by lazy {
            retrofit.create(NewsAPI::class.java)
        }
    }
}