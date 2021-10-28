package com.example.testapplication

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.coredata.data.local.LocalBook
import com.example.coredata.data.remote.api.Datasource
import com.example.coredata.data.repository.BookReposirtory

class MainActivity : AppCompatActivity() {
    lateinit var author: String
    lateinit var adapter: BookRVAdapter
    lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.rv)
        var repo: BookReposirtory? = BookReposirtory(
            this.applicationContext,
            Datasource(),
            LocalBook(this.applicationContext)
        )
        val viewModelFactory = MainViewModelFactory(repo!!)
        val viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)



        findViewById<Button>(R.id.btnsearch).setOnClickListener {
            author = findViewById<TextView>(R.id.authorname).text.toString()
            Log.v("aaaaaaaa", author)
            adapter = BookRVAdapter(context = this)
            viewModel.getBooksAction(author)
            viewModel.bookList.observe(this) {
                it?.let {

                    adapter.bookList = it
                    recyclerView.adapter = adapter
                    recyclerView.layoutManager = LinearLayoutManager(this)
                } ?: kotlin.run { Log.v("nnnnBOokssss", "null") }

            }
        }

    }


}
