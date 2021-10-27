package com.example.core.data.remote.api


import com.example.coredata.domain.entity.Books
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("books/v1/volumes")
     fun getBooks(@Query("q") author: String): Call<Books>


}