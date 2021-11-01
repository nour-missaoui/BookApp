package com.example.testapplication.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.coredata.domain.entity.localentity.BookEntity
import com.example.testapplication.R

class BookRVAdapter(private val listener: ItemListener) :

    RecyclerView.Adapter<BookRVAdapter.ViewHolder>() {
    interface ItemListener {
        fun onLikedPublication(title: String)
    }

    var bookList: List<BookEntity> = listOf()

    class ViewHolder(view: View, private val listener: ItemListener) :
        RecyclerView.ViewHolder(view) {

        val textView: TextView

        init {
            // Define click listener for the ViewHolder's View.
            textView = view.findViewById(R.id.textView)
        }
    }
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.itembooklayout, viewGroup, false)

        return ViewHolder(view, listener)
    }
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.textView.text = bookList[position].title
        viewHolder.textView.setOnClickListener { listener.onLikedPublication(bookList[position].title) }
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = bookList.size

}


