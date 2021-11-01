package com.example.testapplication.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coredata.data.repository.IBookRepository
import com.example.coredata.domain.entity.localentity.BookEntity
import com.example.coredata.domain.utils.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val bookReposirtory: IBookRepository) :
    ViewModel() {


    private val _booklist = MutableLiveData<DataState<List<BookEntity>?>>()
    val bookList: LiveData<DataState<List<BookEntity>?>> = _booklist

    @InternalCoroutinesApi
    fun getBooksAction(author: String) {
        viewModelScope.launch {
            bookReposirtory.getAll(viewModelScope, author).collect { _booklist.value = it }
        }

    }
}