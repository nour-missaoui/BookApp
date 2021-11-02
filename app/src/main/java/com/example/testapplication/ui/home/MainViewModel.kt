package com.example.testapplication.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coredata.data.repository.IBookRepository
import com.example.coredata.domain.utils.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val bookReposirtory: IBookRepository) :
    ViewModel() {

    init {
        processIntentgetbooks()
    }

    //used to send intent from views to modelviews
    val intentChannel = Channel<HomeIntent>(Channel.UNLIMITED)


    //used to send data from  modelviews  to     views
    private val _viewState = MutableStateFlow<HomeViewState>(HomeViewState.Idle)
    val state: StateFlow<HomeViewState> = _viewState

    // process
    private fun processIntentgetbooks() {
        viewModelScope.launch {
            intentChannel.consumeAsFlow().collect {
                when (it) {
                    is HomeIntent.SearchBooksAction -> getAuthorBooks(it.authorName)
                }

            }
        }
    }


    //reduce


    private fun getAuthorBooks(author: String) {
        viewModelScope.launch {
            val dataState = bookReposirtory.getAll(viewModelScope, author).first()
            when (dataState) {
                is DataState.Success -> _viewState.value = HomeViewState.SearchBooks(
                    dataState.data, dataState.isFromRemote
                )
                is DataState.Error -> _viewState.value = HomeViewState.Error(dataState.exception)
            }
        }
    }


    /*
      private val _booklist = MutableLiveData<DataState<List<BookEntity>?>>()
      val bookList: LiveData<DataState<List<BookEntity>?>> = _booklist

        @InternalCoroutinesApi
        fun getBooksAction(author: String) {
            viewModelScope.launch {
                bookReposirtory.getAll(viewModelScope, author).collect { _booklist.value = it }
            }

        }        */

}