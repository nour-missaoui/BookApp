package com.example.core.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.core.domain.entity.localentity.BookEntity

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