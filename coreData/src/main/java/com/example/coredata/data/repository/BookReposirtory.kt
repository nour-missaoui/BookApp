package com.example.coredata.data.repository

//import com.example.core.data.local.BookDao
//import com.example.core.data.local.DataBase
import android.content.Context
import com.example.core.domain.entity.localentity.BookEntity
import com.example.coredata.data.local.LocalBook
import com.example.coredata.data.remote.api.Datasource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

//import com.example.core.domain.entity.localentity.BookEntity


//we need interface to ensure the communication from repo to view
//we implement this interface
//we need interface to ensure the comm from view to repo
// we need to inject this interface
class BookReposirtory(
    private val context: Context,
    private val remoteDS: Datasource,
    private val localdb: LocalBook
) : IBookRepository {
    // function to return flow of books
    @ExperimentalCoroutinesApi
    override suspend fun getAll(scope: CoroutineScope, author: String): Flow<List<BookEntity>?> =
        callbackFlow {

            val bookdata: List<BookEntity>? = null

            bookdata?.let { send(localdb.getLocalBooks()) } ?: kotlin.run {
                remoteDS.getremotebooks(scope, author) { resultbooks ->
                    resultbooks.response?.let { data ->
                        val bookEntitylist: MutableList<BookEntity> = mutableListOf()
                        data.body().items.forEach {
                            bookEntitylist.add(
                                BookEntity(
                                    it.id,
                                    it.volumeInfo.title
                                )
                            )
                        }
                        LocalBook(context!!).insertBooks(bookEntitylist.toList())
                        send(bookEntitylist)
                    }
                }
            }


            awaitClose()
        }

}