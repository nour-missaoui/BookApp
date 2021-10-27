package com.example.testapplication

import com.example.core.data.BookRepository.IBookRepository

//this class is used as intermediate between repo and view
//we inject in this class the interface that the repo implemnts we target to inject the repository
//we call this class in the main activity
class GetList constructor(private val iBoookRepository: IBookRepository, author : String) {

    // init funtion : by default the run funtion when the class is instantiated
    init{
        invokeRepo(author)
    }
    fun invokeRepo(author: String){
        iBoookRepository.getAll(author)

    }
}


