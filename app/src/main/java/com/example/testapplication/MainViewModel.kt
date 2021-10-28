package com.example.testapplication

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.domain.entity.localentity.BookEntity
import com.example.coredata.data.repository.IBookRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainViewModel(bookReposirtory: IBookRepository) : ViewModel() {
    private val bookrepo = bookReposirtory

    private val _booklist = MutableLiveData<List<BookEntity>>()
    val bookList: LiveData<List<BookEntity>> = _booklist
    fun getBooksAction(author: String) {
        viewModelScope.launch {
            bookrepo.getAll(viewModelScope, author).collect {
                _booklist.value = it
            }
        }

    }
}