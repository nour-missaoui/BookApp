package com.example.testapplication.ui.home

sealed class HomeIntent {
    data class SearchBooksAction(val authorName: String) : HomeIntent()
}
