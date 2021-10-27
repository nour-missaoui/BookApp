package com.example.core.data.remote.api

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkCall {

    private const val BASE_URL: String = "https://www.googleapis.com"
val getClient: ApiService
get(){
     val gson : Gson by lazy {
        GsonBuilder().setLenient().create()
    }
    val interceptor = HttpLoggingInterceptor()
    interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

     val httpClient = OkHttpClient.Builder().addInterceptor(interceptor).build()


     val retrofit : Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }


    return    retrofit.create(ApiService::class.java)

}

}