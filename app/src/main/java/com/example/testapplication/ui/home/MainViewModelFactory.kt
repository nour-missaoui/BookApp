package com.example.testapplication.ui.home

/*
class MainViewModelFactory constructor(bookReposirtory: IBookRepository) :
    ViewModelProvider.Factory {
    val bookReposirtory = bookReposirtory
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(MainViewModel::class.java!!)) {
            MainViewModel(bookReposirtory) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}*/