package com.example.coredata.data.remote.api

sealed class StateApi<T>(val data: T?, val message: String?) {

    class Success<T>(data: T) : StateApi<T>(data, null)
    class Error<T>(error: String?) : StateApi<T>(null, error)
}
