package com.example.testapplication

import com.example.coredata.data.repository.IBookRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

//this class is used as intermediate between repo and view
//we inject in this class the interface that the repo implemnts we target to inject the repository
//we call this class in the main activity
class GetList constructor(private val iBoookRepository: IBookRepository, author : String) {

    // init funtion : by default the run funtion when the class is instantiated
    init {
        GlobalScope.launch(Dispatchers.Main) { invokeRepo(author) }

    }

    suspend fun invokeRepo(author: String) {
        //   iBoookRepository.getAll(, author)

    }
}


