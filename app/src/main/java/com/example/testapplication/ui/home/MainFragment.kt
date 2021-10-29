package com.example.testapplication.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.coredata.domain.utils.DataState
import com.example.testapplication.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.InternalCoroutinesApi


@AndroidEntryPoint
class MainFragment : Fragment(), BookRVAdapter.ItemListener {
    lateinit var author: String
    lateinit var adapter: BookRVAdapter
    lateinit var recyclerView: RecyclerView
    val viewModel: MainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    @InternalCoroutinesApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        recyclerView = view.findViewById(R.id.rv)

        view.findViewById<Button>(R.id.btnsearch).setOnClickListener {
            author = view.findViewById<TextView>(R.id.authorname).text.toString()
            Log.v("aaaaaaaa", author)
            adapter = BookRVAdapter(this)
            viewModel.getBooksAction(author)
            viewModel.bookList.observe(viewLifecycleOwner) {
                it?.let {
                    when (it) {
                        is DataState.Success -> {
                            adapter.bookList = it.data!!
                            recyclerView.adapter = adapter
                            recyclerView.layoutManager = LinearLayoutManager(requireContext())
                            if (!it.isFromRemote) {
                                Toast.makeText(
                                    requireContext(),
                                    "data frm local",
                                    Toast.LENGTH_LONG
                                ).show()
                            } else {
                                if (!it.isFromRemote) {
                                    Toast.makeText(
                                        requireContext(),
                                        "data frm remote",
                                        Toast.LENGTH_LONG
                                    ).show()
                                }
                            }
                        }
                        is DataState.Error -> Toast.makeText(
                            requireContext(),
                            it.exception,
                            Toast.LENGTH_LONG
                        )
                            .show()
                    }

                } ?: kotlin.run { Log.v("nnnnBOokssss", "null") }

            }
        }
    }

    override fun onLikedPublication(title: String) {
        findNavController().navigate(MainFragmentDirections.actionMainFragmentToSecondFragment(title))


    }


}