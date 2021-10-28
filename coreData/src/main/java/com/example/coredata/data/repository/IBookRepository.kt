package com.example.coredata.data.repository

import com.example.core.domain.entity.localentity.BookEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

// interface to communicate from the view to repo
interface IBookRepository {
    suspend fun getAll(scope: CoroutineScope, author: String): Flow<List<BookEntity>?>
}