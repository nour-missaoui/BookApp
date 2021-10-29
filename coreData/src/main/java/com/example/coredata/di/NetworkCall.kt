package com.example.coredata.di

import android.content.Context
import com.example.core.data.local.BookDao
import com.example.core.data.local.DataBase
import com.example.core.data.remote.api.ApiService
import com.example.coredata.data.local.LocalBook
import com.example.coredata.data.remote.api.Datasource
import com.example.coredata.data.repository.BookReposirtory
import com.example.coredata.data.repository.IBookRepository
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkCall {
    private const val BASE_URL: String = "https://www.googleapis.com"

    @Singleton
    @Provides
    fun providehttpClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        return OkHttpClient.Builder().addInterceptor(interceptor).build()
    }

    @Singleton
    @Provides
    fun provideretrofit(httpClient: OkHttpClient): Retrofit {
        val gson: Gson by lazy {
            GsonBuilder().setLenient().create()
        }

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    @Singleton
    @Provides
    fun provideClient(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideremotedatasource(apiService: ApiService): Datasource {
        return Datasource(apiService)
    }

    //local
    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): DataBase {

        return DataBase.getInstance(context = appContext)
    }


    @Singleton
    @Provides
    fun provideDao(dataBase: DataBase): BookDao {
        return dataBase.bookDao()
    }

    @Singleton
    @Provides
    fun providelocaldatasource(bookDao: BookDao): LocalBook {
        return LocalBook(bookDao)
    }

    //repo
    @Singleton
    @Provides
    fun providerepository(localBook: LocalBook, datasource: Datasource): IBookRepository {
        return BookReposirtory(datasource, localBook)
    }


}

