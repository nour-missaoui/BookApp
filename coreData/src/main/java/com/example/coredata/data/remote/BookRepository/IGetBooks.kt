package com.example.core.data.BookRepository

import com.example.core.domain.entity.localentity.BookEntity
import com.example.coredata.domain.entity.Books


// interface to communicate from repo to view
// we use interfaces to ensure the abstraction between classes

interface IGetBooks {
    fun getBoooks(books: List<BookEntity>?)
}