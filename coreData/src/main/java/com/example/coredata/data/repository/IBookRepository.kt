package com.example.coredata.data.repository

import com.example.coredata.domain.entity.localentity.BookEntity
import com.example.coredata.domain.utils.DataState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

// interface to communicate from the view to repo
interface IBookRepository {
    suspend fun getAll(scope: CoroutineScope, author: String): Flow<DataState<List<BookEntity>?>>
}