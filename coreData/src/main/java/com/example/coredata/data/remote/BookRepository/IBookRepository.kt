package com.example.core.data.BookRepository

// interface to communicate from the view to repo
interface IBookRepository {
    fun getAll(author : String)
}