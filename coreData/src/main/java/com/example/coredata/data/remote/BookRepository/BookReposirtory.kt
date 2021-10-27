package com.example.core.data.BookRepository

import android.content.Context
import android.util.Log
import com.example.core.data.local.DataBase
import com.example.coredata.domain.entity.Books
//import com.example.core.data.local.BookDao
//import com.example.core.data.local.DataBase
import com.example.core.data.remote.api.NetworkCall
import com.example.core.domain.entity.localentity.BookEntity
//import com.example.core.domain.entity.localentity.BookEntity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


//we need interface to ensure the communication from repo to view
//we implement this interface
//we need interface to ensure the comm from view to repo
// we need to inject this interface
class BookReposirtory : IBookRepository {
    var getBooks : IGetBooks? = null
    var context : Context? = null

constructor(getbooks: IGetBooks, context: Context){
    this.getBooks = getbooks
    this.context = context
}
    override fun getAll(author : String) {
        val call: Call<Books> = NetworkCall.getClient.getBooks(author)
        val db = DataBase.getInstance(context!!)

        call.enqueue(object : Callback<Books> {
            override fun onFailure(call: Call<Books>?, t: Throwable?) {
                getBooks!!.getBoooks(null)
            }

            override fun onResponse(
                call: Call<Books>?,
                response: Response<Books>?
            ) {

            //insert books to db
               val books : List<BookEntity>? = null
                response!!.body().items.forEach {books?.toMutableList()?.add(BookEntity(it.id, it.volumeInfo.title)) }
                Log.v("bookslist", books.toString())
                db.bookDao().insertBook(books)
            //get books from db

                getBooks!!.getBoooks(db.bookDao().getBooks())
            }

        })
    }




}