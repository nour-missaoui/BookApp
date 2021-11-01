package com.example.coredata.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.coredata.domain.entity.localentity.BookEntity

@Database(
    entities = [BookEntity::class],
    version = 1,
    exportSchema = false
)

abstract class DataBase: RoomDatabase(){
 abstract fun bookDao(): BookDao
    companion object{
        @Volatile
        private var instance: DataBase? = null
        fun getInstance(context: Context) = instance ?: synchronized(this) {
            instance ?: Room.databaseBuilder(
                context.applicationContext,
                DataBase::class.java,
                "application.db"
            )
                .build()
        }
    }

}