package com.example.coredata.data.local

import com.example.core.data.local.BookDao
import com.example.core.domain.entity.localentity.BookEntity
import javax.inject.Inject

class LocalBook @Inject constructor(private val bookDao: BookDao) {


    fun getLocalBooks(): List<BookEntity>? {
        return bookDao.getBooks()
    }

    suspend fun insertBooks(bookEntity: List<BookEntity>) {
        bookDao.insertBook(bookEntity = bookEntity)
    }


}