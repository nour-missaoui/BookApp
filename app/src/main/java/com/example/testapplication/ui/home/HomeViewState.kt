package com.example.testapplication.ui.home

import androidx.constraintlayout.motion.utils.ViewState
import com.example.coredata.domain.entity.localentity.BookEntity

sealed class HomeViewState : ViewState() {
    object Idle : HomeViewState()
    data class SearchBooks(val books: List<BookEntity>?, val isFromRemote: Boolean) :
        HomeViewState()

    data class Error(val error: String) : HomeViewState()

}