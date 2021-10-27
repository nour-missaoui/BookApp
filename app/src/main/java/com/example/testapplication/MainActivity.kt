package com.example.testapplication

import com.example.coredata.domain.entity.Books
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.core.data.BookRepository.BookReposirtory
import com.example.core.data.BookRepository.IGetBooks
import com.example.core.domain.entity.localentity.BookEntity

class MainActivity : AppCompatActivity()
    , IGetBooks
{
    lateinit var author: String
    lateinit var adapter : BookRVAdapter
    lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.rv)



        findViewById<Button>(R.id.btnsearch).setOnClickListener {
            author = findViewById<TextView>(R.id.authorname).text.toString()
            Log.v("aaaaaaaa", author)
            //we call Getlist class to get data , we inject the repo that have as a constructor the interface we implemnted in this class
            //our target is to ensure that this interfaces use the body code of overrided functions developed in this class
            GetList(BookReposirtory( this, this), author) }
    }

    override fun getBoooks(books: List<BookEntity>?) {
        Log.v("GetBOoks", "GetBOoksGetBOoksGetBOoks")
            adapter = BookRVAdapter(this)
            books?.let {

                adapter.bookList = books
                recyclerView.adapter =adapter
                recyclerView.layoutManager = LinearLayoutManager(this)
            }?: kotlin.run {    Log.v("nnnnBOokssss", "null") }

        }
    }
