package com.example.coredata.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.coredata.domain.entity.localentity.BookEntity

@Dao
interface BookDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBook(bookEntity: List<BookEntity>?)

    @Query("select * from BOOK_TABLE")
    fun getBooks(): List<BookEntity>?


}