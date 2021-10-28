package com.example.coredata.data.repository

import com.example.core.domain.entity.localentity.BookEntity


// interface to communicate from repo to view
// we use interfaces to ensure the abstraction between classes

interface IGetBooks {
    fun getBoooks(books: List<BookEntity>?)
}