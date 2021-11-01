package com.example.coredata.data.repository

//import com.example.coredata.data.local.BookDao
//import com.example.coredata.data.local.DataBase
import com.example.coredata.data.local.LocalBook
import com.example.coredata.data.remote.api.Datasource
import com.example.coredata.domain.entity.Books
import com.example.coredata.domain.entity.localentity.BookEntity
import com.example.coredata.domain.utils.DataState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

//import com.example.coredata.domain.entity.localentity.BookEntity


//we need interface to ensure the communication from repo to view
//we implement this interface
//we need interface to ensure the comm from view to repo
// we need to inject this interface
class BookReposirtory @Inject constructor(
    private val remoteDS: Datasource,
    private val localdb: LocalBook
) : IBookRepository {
    // function to return flow of books
    @ExperimentalCoroutinesApi
    override suspend fun getAll(
        scope: CoroutineScope,
        author: String
    ): Flow<DataState<List<BookEntity>?>> {

        return managedata(scope, author)
    }

    @ExperimentalCoroutinesApi
    suspend fun managedata(
        scope: CoroutineScope,
        author: String
    ): Flow<DataState<List<BookEntity>?>> =

        callbackFlow {
            remoteDS.getremotebooks<Books>(scope, author) { resultbooks ->
                when (resultbooks) {
                    is DataState.Success -> {
                        resultbooks.data.let { data ->
                            val bookEntitylist: MutableList<BookEntity> = mutableListOf()
                            data?.forEach {
                                bookEntitylist.add(
                                    it
                                )
                            }

                            localdb.insertBooks(bookEntitylist.toList())

                            send(DataState.Success(bookEntitylist, true))
                        }
                    }
                    is DataState.Error -> {
                        scope.launch(Dispatchers.IO) {
                            val localdata = localdb.getLocalBooks()
                            localdata?.let {
                                send(DataState.Success(it, false))
                            } ?: kotlin.run { send(DataState.Error("No data")) }
                        }

                    }
                }

            }
            awaitClose()
        }


}


