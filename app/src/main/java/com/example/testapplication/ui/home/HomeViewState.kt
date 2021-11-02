package com.example.testapplication.ui.home

import androidx.constraintlayout.motion.utils.ViewState

sealed class HomeViewState : ViewState() {
    object Idle : HomeViewState()
    data class SearchBooks<T>(val books: T) : HomeViewState()
    data class Error(val error: String) : HomeViewState()

}