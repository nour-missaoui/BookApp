package com.example.coredata.data.remote.api

import com.example.core.data.remote.api.NetworkCall
import com.example.coredata.domain.entity.Books
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Datasource {
    // function to return api response
    suspend fun getremotebooks(
        scope: CoroutineScope,
        author: String,
        callback: suspend (DataResponse) -> Unit
    ) {
        scope.launch(Dispatchers.IO) {
            NetworkCall.getClient.getBooks(author).enqueue(object : Callback<Books> {
                override fun onFailure(call: Call<Books>?, t: Throwable?) {
                    scope.launch {
                        callback(DataResponse(null, t))
                    }
                }

                override fun onResponse(
                    call: Call<Books>?,
                    response: Response<Books>?
                ) {
                    scope.launch {
                        callback(DataResponse(response, null))
                    }
                }
            })
        }

    }
}

data class DataResponse(val response: Response<Books>?, val t: Throwable?)

