package com.example.coredata.domain.utils


sealed class DataState<out R> {
    data class Success<out T>(val data: T, val isFromRemote: Boolean) : DataState<T>()
    data class Error(val exception: String) : DataState<Nothing>()
}
