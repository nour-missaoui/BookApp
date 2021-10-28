package com.example.coredata.data.local

import android.content.Context
import com.example.core.data.local.DataBase
import com.example.core.domain.entity.localentity.BookEntity

class LocalBook(context: Context) {

    val db = DataBase.getInstance(context)
    fun getLocalBooks(): List<BookEntity>? {
        return db.bookDao().getBooks()
    }

    suspend fun insertBooks(bookEntity: List<BookEntity>) {
        db.bookDao().insertBook(bookEntity = bookEntity)
    }

}