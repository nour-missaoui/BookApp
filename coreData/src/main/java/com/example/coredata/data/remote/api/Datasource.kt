package com.example.coredata.data.remote.api

import com.example.coredata.domain.entity.Books
import com.example.coredata.domain.entity.localentity.BookEntity
import com.example.coredata.domain.utils.DataState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class Datasource @Inject constructor(
    private val apiService: ApiService
) {
    // function to return api response
    suspend fun <T> getremotebooks(
        scope: CoroutineScope,
        author: String,
        callback: suspend (DataState<List<BookEntity>?>) -> Unit
    ) {
        scope.launch(Dispatchers.IO) {

            apiService.getBooks(author).enqueue(object : Callback<Books> {
                override fun onFailure(call: Call<Books>?, t: Throwable?) {
                    scope.launch {

                        callback(DataState.Error(t.toString()))


                    }
                }

                override fun onResponse(
                    call: Call<Books>?,
                    response: Response<Books>?
                ) {
                    scope.launch {
                        if (response!!.isSuccessful) {
                            response.body()?.let {
                                val bookEntitylist: MutableList<BookEntity> = mutableListOf()
                                response.body()?.items?.forEach {
                                    bookEntitylist.add(
                                        BookEntity(
                                            it.id,
                                            it.volumeInfo.title
                                        )
                                    )
                                }

                                callback(DataState.Success(bookEntitylist, true))
                            } ?: kotlin.run { DataState.Error("No Data") }

                        } else {
                            callback(DataState.Error(response.errorBody().toString()))
                        }


                    }
                }
            })
        }

    }
}

data class DataResponse(val response: Response<Books>?, val t: Throwable?)

